package org.hurricanegames.creativeitemfilter.handler;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.hurricanegames.creativeitemfilter.CreativeItemFilter;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.handler.meta.MetaCopierFactory;
import org.hurricanegames.creativeitemfilter.utils.ComponentUtils;
import org.jetbrains.annotations.Nullable;
import uk.co.notnull.messageshelper.Message;

@SuppressWarnings("UnstableApiUsage")
public class CreativeItemFilterHandler implements Listener {
	private static final ItemFlag[] ITEM_FLAGS_EMPTY = new ItemFlag[0];

	private final Logger logger;
	private final CreativeItemFilterConfiguration configuration;
	private final MetaCopierFactory metaCopierFactory;

	public CreativeItemFilterHandler(Logger logger, MetaCopierFactory factory, CreativeItemFilterConfiguration configuration) {
		this.logger = logger;
		this.configuration = configuration;
		this.metaCopierFactory = factory;
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

			if (oldItem.hasItemMeta()) {
				ItemMeta oldMeta = oldItem.getItemMeta();
				ItemMeta newMeta = Bukkit.getItemFactory().getItemMeta(newItem.getType());

				metaCopierFactory.getCopiers(oldMeta).forEach(copier -> copier.copyValidMeta(configuration, oldMeta, newMeta, oldItem.getType()));

				if (oldMeta instanceof Damageable oldMetaDamageable) {
					if(oldMetaDamageable.hasMaxDamage()) {
						// max_damage component
						((Damageable) newMeta).setMaxDamage(Math.min(oldMetaDamageable.getMaxDamage(), configuration.getDamageMax()));

						// damage component
						if(oldMetaDamageable.hasDamage()) {
							((Damageable) newMeta).setDamage(Math.min(oldMetaDamageable.getDamage(), ((Damageable) newMeta).getMaxDamage()));
						}
					} else if(oldMetaDamageable.hasDamage()) {
						// damage component
						((Damageable) newMeta).setDamage(oldMetaDamageable.getDamage());
					}
				}

				// enchantment_glint_override component
				if (oldMeta.hasEnchantmentGlintOverride()) {
					newMeta.setEnchantmentGlintOverride(oldMeta.getEnchantmentGlintOverride());
				}

				// repair_cost component
				if (oldMeta instanceof Repairable) {
					((Repairable) newMeta).setRepairCost(((Repairable) oldMeta).getRepairCost());
				}

				// custom_model_data component
				if (oldMeta.hasCustomModelData()) {
					newMeta.setCustomModelDataComponent(oldMeta.getCustomModelDataComponent());
				}

				// item_model component
				if (oldMeta.hasItemModel()) {
					newMeta.setItemModel(oldMeta.getItemModel());
				}

				// max_stack_size component
				if(oldMeta.hasMaxStackSize()) {
					newMeta.setMaxStackSize(Math.min(oldMeta.getMaxStackSize(), configuration.getStackSizeMax()));
				}

				// hide_tooltip component
				newMeta.setHideTooltip(oldMeta.isHideTooltip());

				// damage_resistant component
				if(oldMeta.hasDamageResistant()) {
					newMeta.setDamageResistant(oldMeta.getDamageResistant());
				}

				// rarity component
				if(oldMeta.hasRarity()) {
					newMeta.setRarity(oldMeta.getRarity());
				}

				newMeta.addItemFlags(oldMeta.getItemFlags().toArray(ITEM_FLAGS_EMPTY));

				int nameMaxLength = configuration.getDisplayNameMaxLength();

				// custom_name component
				if (oldMeta.hasDisplayName() && ComponentUtils.validateComponent(oldMeta.displayName(), nameMaxLength)) {
					newMeta.displayName(oldMeta.displayName());
				}

				// item_name component
				if (oldMeta.hasItemName() && ComponentUtils.validateComponent(oldMeta.itemName(), nameMaxLength)) {
					newMeta.itemName(oldMeta.itemName());
				}

				int loreMaxLength = configuration.getLoreMaxLength();

				// lore component
				if (oldMeta.hasLore()) {
					newMeta.lore(
						oldMeta.lore().stream()
						.filter(line -> ComponentUtils.validateComponent(line, loreMaxLength))
						.limit(configuration.getLoreMaxCount())
						.collect(Collectors.toList())
					);
				}

				newItem.setItemMeta(newMeta);
				newItem.setAmount(Math.min(oldItem.getAmount(), newItem.getMaxStackSize()));
			}

			// enchantments component
			int enchantmentMaxLevel = configuration.getEnchantmentMaxLevel();
			oldItem.getEnchantments().entrySet().stream()
			.filter(entry -> (entry.getValue() > 0) && (entry.getValue() <= enchantmentMaxLevel))
			.filter(configuration.getEnchantmentRemoveUnapplicableEnabled() ? entry -> entry.getKey().canEnchantItem(newItem) : entry -> true)
			.limit(configuration.getEnchantmentMaxCount())
			.forEach(entry -> newItem.addUnsafeEnchantment(entry.getKey(), entry.getValue()));

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
