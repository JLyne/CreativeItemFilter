package org.hurricanegames.creativeitemfilter;

import org.bukkit.plugin.Plugin;

public class CreativeItemFilterConfiguration {
	private final Plugin plugin;

	public CreativeItemFilterConfiguration(Plugin plugin) {
		this.plugin = plugin;
	}

	public int getEnchantmentMaxLevel() {
		return plugin.getConfig().getInt("enchantment.max_level");
	}

	public int getEnchantmentMaxCount() {
		return plugin.getConfig().getInt("enchantment.max_count");
	}

	public boolean getEnchantmentRemoveUnapplicableEnabled() {
		return plugin.getConfig().getBoolean("enchantment.remove_unapplicable");
	}

	public int getDisplayNameMaxLength() {
		return plugin.getConfig().getInt("display_name.max_length");
	}

	public int getLoreMaxLength() {
		return plugin.getConfig().getInt("lore.max_length");
	}

	public int getLoreMaxCount() {
		return plugin.getConfig().getInt("lore.max_count");
	}

	public int getEnchantmentBookMaxLevel() {
		return plugin.getConfig().getInt("enchantment_book.max_level");
	}

	public int getEnchantmentBookMaxCount() {
		return plugin.getConfig().getInt("enchantment_book.max_count");
	}

	public int getBookAuthorMaxLength() {
		return plugin.getConfig().getInt("book.author.max_length");
	}

	public int getBookTitleMaxLength() {
		return plugin.getConfig().getInt("book.title.max_length");
	}

	public int getBookPagesMaxLength() {
		return plugin.getConfig().getInt("book.pages.max_length");
	}

	public int getBookPagesMaxCount() {
		return plugin.getConfig().getInt("book.pages.max_count");
	}

	public int getPotionEffectsMaxAmplifier() {
		return plugin.getConfig().getInt("potion.effects.max_amplifier");
	}

	public long getPotionEffectsMaxDuration() {
		return plugin.getConfig().getInt("potion.effects.max_duration");
	}

	public int getPotionEffectsMaxCount() {
		return plugin.getConfig().getInt("potion.effects.max_count");
	}

	public int getFireworkEffectColorsMaxCount() {
		return plugin.getConfig().getInt("firework_effect.colors.max_count");
	}

	public int getFireworkMaxPower() {
		return plugin.getConfig().getInt("firework.max_power");
	}

	public int getFireworkMaxEffects() {
		return plugin.getConfig().getInt("firework.max_effects");
	}

	public int getKnowledgeBookMaxRecipes() {
		return plugin.getConfig().getInt("knowledge_book.max_recipes");
	}

	public int getDamageMax() {
		return plugin.getConfig().getInt("damage.max");
	}
}
