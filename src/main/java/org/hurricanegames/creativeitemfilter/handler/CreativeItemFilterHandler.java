package org.hurricanegames.creativeitemfilter.handler;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.event.block.BlockPreDispenseEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CrafterCraftEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilter;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.handler.component.ItemComponentPopulatorFactory;
import org.hurricanegames.creativeitemfilter.handler.meta.MetaCopierFactory;
import org.jetbrains.annotations.Nullable;
import uk.co.notnull.messageshelper.Message;

@SuppressWarnings("UnstableApiUsage")
public class CreativeItemFilterHandler implements Listener {
	private final Logger logger;
	private final CreativeItemFilterConfiguration configuration;
	private final MetaCopierFactory metaCopierFactory;
	private final ItemComponentPopulatorFactory componentPopulatorFactory;

	public CreativeItemFilterHandler(Logger logger, MetaCopierFactory metaFactory,
									 ItemComponentPopulatorFactory componentFactory,
									 CreativeItemFilterConfiguration configuration) {
		this.logger = logger;
		this.configuration = configuration;
		this.metaCopierFactory = metaFactory;
		this.componentPopulatorFactory = componentFactory;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();

		if(shouldBlock(item, player)) {
			event.setCancelled(true);
			player.getInventory().remove(item.getType());
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onItemCraft(CraftItemEvent event) {
		if(shouldBlock(event.getRecipe().getResult(), (Player) event.getWhoClicked())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onCrafterCraft(CrafterCraftEvent event) {
		if(shouldBlock(event.getResult(), null)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPreDispense(BlockPreDispenseEvent event) {
		if(shouldBlock(event.getItemStack(), null)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onDropItem(PlayerDropItemEvent event) {
		if(shouldBlock(event.getItemDrop().getItemStack(), event.getPlayer())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPickupItem(PlayerAttemptPickupItemEvent event) {
		if(shouldBlock(event.getItem().getItemStack(), event.getPlayer())) {
			event.setCancelled(true);
		}
	}

	//TODO: Specific material ItemMeta may extend multiple ItemMeta interfaces, so the code needs to be adapted to be able to handle that
	@EventHandler(priority = EventPriority.HIGH)
	public void onCreativeItemEvent(InventoryCreativeEvent event) {
		try {
			ItemStack oldItem = event.getCursor();
			Player player = (Player) event.getWhoClicked();

			if(shouldBlock(oldItem, player)) {
				event.setCancelled(true);
				((Player) event.getWhoClicked()).updateInventory();
				return;
			}

			if(player.hasPermission("creativeitemfilter.bypass.filter")) {
				return;
			}

			ItemStack newItem = new ItemStack(oldItem.getType(), oldItem.getAmount());
			Set<DataComponentType> oldDataTypes = oldItem.getDataTypes();

			// Copy removal of default components from old item
			newItem.getDataTypes().stream().filter(type -> !oldDataTypes.contains(type))
					.forEach(newItem::resetData);

			//TODO: Remove once everything is using the new api
			if (oldItem.hasItemMeta()) {
				ItemMeta oldMeta = oldItem.getItemMeta();
				ItemMeta newMeta = Bukkit.getItemFactory().getItemMeta(newItem.getType());

				metaCopierFactory.getCopiers(oldMeta).forEach(copier -> copier.copyValidMeta(configuration, oldMeta, newMeta, oldItem.getType()));

				newItem.setItemMeta(newMeta);
			}

			componentPopulatorFactory.getPopulators().forEach(
					populator -> populator.populateComponents(oldItem, newItem, configuration));

			newItem.setAmount(Math.min(oldItem.getAmount(), newItem.getMaxStackSize()));
			event.setCursor(newItem);
		} catch (Throwable t) {
			event.setCancelled(true);
			((Player) event.getWhoClicked()).updateInventory();
			logger.log(Level.WARNING, "Unable to create safe clone of creative itemstack, removing", t);
		}
	}

	private boolean shouldBlock(@Nullable ItemStack item, @Nullable Player player) {
		if(item == null || player != null && player.hasPermission("creativeitemfilter.bypass.blacklist")) {
			return false;
		}

		if(configuration.isItemBlacklisted(item.getType())) {
			if(player != null) {
				blacklistNotify(player, item);
			}

			return true;
		}

		return false;
	}

	private void blacklistNotify(Player player, ItemStack item) {
		logger.info(String.format("Filtered blacklisted item %s from %s", item.getType(), player.getName()));
		CreativeItemFilter.getMessagesHelper().send(player, Message
				.builder("message.item-filtered")
				.prefixed()
				.type(Message.MessageType.ERROR)
				.replacement("item", item.displayName())
				.build());
	}
}
