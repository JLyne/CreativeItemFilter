package org.hurricanegames.creativeitemfilter.handler;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.handler.meta.MetaCopierFactory;
import org.hurricanegames.creativeitemfilter.utils.StringUtils;

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

	//TODO: Specific material ItemMeta may extend multiple ItemMeta interfaces, so the code needs to be adapted to be able to handle that
	@EventHandler
	public void onCreativeItemEvent(InventoryCreativeEvent event) {
		try {
			ItemStack oldItem = event.getCursor();

			ItemStack newItem = new ItemStack(oldItem.getType(), oldItem.getAmount());

			if (oldItem.hasItemMeta()) {
				ItemMeta oldMeta = oldItem.getItemMeta();
				ItemMeta newMeta = Bukkit.getItemFactory().getItemMeta(newItem.getType());

				metaCopierFactory.getCopiers(oldMeta).forEach(copier -> copier.copyValidMeta(configuration, oldMeta, newMeta));

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

				if (oldMeta instanceof Repairable) {
					((Repairable) newMeta).setRepairCost(((Repairable) oldMeta).getRepairCost());
				}

				// custom_model_data component
				if (oldMeta.hasCustomModelData()) {
					newMeta.setCustomModelData(oldMeta.getCustomModelData());
				}

				// max_stack_size component
				if(oldMeta.hasMaxStackSize()) {
					newMeta.setMaxStackSize(Math.min(oldMeta.getMaxStackSize(), configuration.getStackSizeMax()));
				}

				newMeta.addItemFlags(oldMeta.getItemFlags().toArray(ITEM_FLAGS_EMPTY));

				if (oldMeta.hasDisplayName()) {
					newMeta.setDisplayName(StringUtils.clampString(oldMeta.getDisplayName(), configuration.getDisplayNameMaxLength()));
				}

				int loreMaxLength = configuration.getLoreMaxLength();
				if (oldMeta.hasLore()) {
					newMeta.setLore(
						oldMeta.getLore().stream()
						.map(loreLine -> StringUtils.clampString(loreLine, loreMaxLength))
						.limit(configuration.getLoreMaxCount())
						.collect(Collectors.toList())
					);
				}

				newItem.setItemMeta(newMeta);
			}

			int enchantmentMaxLevel = configuration.getEnchantmentMaxLevel();
			oldItem.getEnchantments().entrySet().stream()
			.filter(entry -> (entry.getValue() > 0) && (entry.getValue() <= enchantmentMaxLevel))
			.filter(configuration.getEnchantmentRemoveUnapplicableEnabled() ? entry -> entry.getKey().canEnchantItem(newItem) : entry -> true)
			.limit(configuration.getEnchantmentMaxCount())
			.forEach(entry -> newItem.addUnsafeEnchantment(entry.getKey(), entry.getValue()));

			event.setCursor(newItem);
		} catch (Throwable t) {
			event.setCursor(null);
			((Player) event.getWhoClicked()).updateInventory();
			logger.log(Level.WARNING, "Unable to create safe clone of creative itemstack, removing", t);
		}
	}

}
