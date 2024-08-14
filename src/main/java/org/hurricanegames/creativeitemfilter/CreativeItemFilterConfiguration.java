package org.hurricanegames.creativeitemfilter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public final class CreativeItemFilterConfiguration {
	private final Plugin plugin;
	private int enchantmentMaxLevel;
	private int enchantmentMaxCount;
	private boolean enchantmentRemoveUnapplicable;
	private int displayNameMaxLength;
	private int loreMaxLength;
	private int loreMaxCount;
	private int enchantedBookMaxLevel;
	private int enchantedBookMaxCount;
	private int bookAuthorMaxLength;
	private int bookTitleMaxLength;
	private int bookPagesMaxLength;
	private int bookPagesMaxCount;
	private int potionMaxAmplifier;
	private int potionMaxDuration;
	private int potionMaxCount;
	private int fireworkMaxColors;
	private int fireworkMaxPower;
	private int fireworkMaxEffects;
	private int knowledgeBookMaxRecipes;
	private int componentsMaxChildDepth;
	private int componentsMaxArgumentDepth;
	private int componentsMaxArgumentCount;
	private int componentsMaxChildCount;
	private int damageMax;
	private int stackSizeMax;

	public CreativeItemFilterConfiguration(Plugin plugin) {
		this.plugin = plugin;

		reloadValues();
	}

	public void reloadValues() {
		ConfigurationSection config = plugin.getConfig();

		enchantmentMaxLevel = config.getInt("enchantment.max_level", 15);
		enchantmentMaxCount = config.getInt("enchantment.max_count", 6);
		enchantmentRemoveUnapplicable = config.getBoolean("enchantment.remove_unapplicable");
		displayNameMaxLength = config.getInt("display_name.max_length", 64);
		loreMaxCount = config.getInt("lore.max_count", 32);
		loreMaxLength = config.getInt("lore.max_length", 64);
		enchantedBookMaxLevel = config.getInt("enchantment_book.max_level", 15);
		enchantedBookMaxCount = config.getInt("enchantment_book.max_count", 6);
		bookAuthorMaxLength = config.getInt("book.author.max_length", 16);
		bookTitleMaxLength = config.getInt("book.title.max_length", 32);
		bookPagesMaxLength = config.getInt("book.pages.max_length", 255);
		bookPagesMaxCount = config.getInt("book.pages.max_count", 20);
		potionMaxAmplifier = config.getInt("potion.effects.max_amplifier", 5);
		potionMaxDuration = config.getInt("potion.effects.max_duration", 12000);
		potionMaxCount = config.getInt("potion.effects.max_count", 4);
		fireworkMaxColors = config.getInt("firework_effect.colors.max_count", 16);
		fireworkMaxPower = config.getInt("firework.max_power", 64);
		fireworkMaxEffects = config.getInt("firework.max_effects", 8);
		knowledgeBookMaxRecipes = config.getInt("knowledge_book.max_recipes", 10);
		componentsMaxChildDepth = config.getInt("component.max_child_depth", 10);
		componentsMaxArgumentDepth = config.getInt("component.max_translation_depth", 3);
		componentsMaxArgumentCount = config.getInt("component.max_translation_arguments", 10);
		componentsMaxChildCount = config.getInt("component.max_child_count", 100);
		damageMax = config.getInt("damage.max", 2048);
		stackSizeMax = config.getInt("stack_size.max", 64);
	}

	public int getEnchantmentMaxLevel() {
		return enchantmentMaxLevel;
	}

	public int getEnchantmentMaxCount() {
		return enchantmentMaxCount;
	}

	public boolean getEnchantmentRemoveUnapplicableEnabled() {
		return enchantmentRemoveUnapplicable;
	}

	public int getDisplayNameMaxLength() {
		return displayNameMaxLength;
	}

	public int getLoreMaxLength() {
		return loreMaxLength;
	}

	public int getLoreMaxCount() {
		return loreMaxCount;
	}

	public int getEnchantmentBookMaxLevel() {
		return enchantedBookMaxLevel;
	}

	public int getEnchantmentBookMaxCount() {
		return enchantedBookMaxCount;
	}

	public int getBookAuthorMaxLength() {
		return bookAuthorMaxLength;
	}

	public int getBookTitleMaxLength() {
		return bookTitleMaxLength;
	}

	public int getBookPagesMaxLength() {
		return bookPagesMaxLength;
	}

	public int getBookPagesMaxCount() {
		return bookPagesMaxCount;
	}

	public int getPotionEffectsMaxAmplifier() {
		return potionMaxAmplifier;
	}

	public long getPotionEffectsMaxDuration() {
		return potionMaxDuration;
	}

	public int getPotionEffectsMaxCount() {
		return potionMaxCount;
	}

	public int getFireworkEffectColorsMaxCount() {
		return fireworkMaxColors;
	}

	public int getFireworkMaxPower() {
		return fireworkMaxPower;
	}

	public int getFireworkMaxEffects() {
		return fireworkMaxEffects;
	}

	public int getKnowledgeBookMaxRecipes() {
		return knowledgeBookMaxRecipes;
	}

	public int getComponentMaxChildDepth() {
		return componentsMaxChildDepth;
	}

	public int getComponentMaxTranslationDepth() {
		return componentsMaxArgumentDepth;
	}

	public int getComponentMaxTranslationArguments() {
		return componentsMaxArgumentCount;
	}

	public int getComponentMaxChildCount() {
		return componentsMaxChildCount;
	}

	public int getDamageMax() {
		return damageMax;
	}

	public int getStackSizeMax() {
		return stackSizeMax;
	}
}
