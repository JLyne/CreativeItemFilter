package org.hurricanegames.creativeitemfilter;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.hurricanegames.commandlib.configurations.ConfigurationUtils.IntegerConfigurationField;
import org.hurricanegames.commandlib.configurations.ConfigurationUtils.LongConfigurationField;
import org.hurricanegames.commandlib.configurations.SimpleConfiguration;

public class CreativeItemFilterConfiguration extends SimpleConfiguration {

	private final File storageFile;

	public CreativeItemFilterConfiguration(File storageFile) {
		this.storageFile = storageFile;
	}

	@Override
	protected File getStorageFile() {
		return storageFile;
	}

	@ConfigurationFieldDefinition(fieldName = "enchantment.max_level", fieldType = IntegerConfigurationField.class)
	private Integer enchantmentMaxLevel = Integer.valueOf(15);
	@ConfigurationFieldDefinition(fieldName = "enchantment.max_level", fieldType = IntegerConfigurationField.class)
	private Integer enchantmentMaxCount = Integer.valueOf(6);
	@ConfigurationFieldDefinition(fieldName = "enchantment.remove_unapplicable")
	private Boolean enchantmentRemoveUnapplicable = Boolean.FALSE;

	@ConfigurationFieldDefinition(fieldName = "display_name.max_length", fieldType = IntegerConfigurationField.class)
	private Integer displayNameMaxLength = Integer.valueOf(64);

	@ConfigurationFieldDefinition(fieldName = "lore.max_length", fieldType = IntegerConfigurationField.class)
	private Integer loreMaxLength = Integer.valueOf(64);
	@ConfigurationFieldDefinition(fieldName = "lore.max_count", fieldType = IntegerConfigurationField.class)
	private Integer loreMaxCount = Integer.valueOf(32);

	@ConfigurationFieldDefinition(fieldName = "enchantment_book.max_level", fieldType = IntegerConfigurationField.class)
	private Integer enchantmentBookMaxLevel = Integer.valueOf(15);
	@ConfigurationFieldDefinition(fieldName = "enchantment_book.max_count", fieldType = IntegerConfigurationField.class)
	private Integer enchantmentBookMaxCount = Integer.valueOf(6);

	@ConfigurationFieldDefinition(fieldName = "book.author.max_length", fieldType = IntegerConfigurationField.class)
	private Integer bookAuthorMaxLength = Integer.valueOf(16);
	@ConfigurationFieldDefinition(fieldName = "book.title.max_length", fieldType = IntegerConfigurationField.class)
	private Integer bookTitleMaxLength = Integer.valueOf(64);
	@ConfigurationFieldDefinition(fieldName = "book.pages.max_length", fieldType = IntegerConfigurationField.class)
	private Integer bookPagesMaxLength = Integer.valueOf(255);
	@ConfigurationFieldDefinition(fieldName = "book.pages.max_count", fieldType = IntegerConfigurationField.class)
	private Integer bookPagesMaxCount = Integer.valueOf(20);

	@ConfigurationFieldDefinition(fieldName = "potion.effects.max_amplifier", fieldType = IntegerConfigurationField.class)
	private Integer potionEffectMaxAmplifier = Integer.valueOf(5);
	@ConfigurationFieldDefinition(fieldName = "potion.effects.max_duration", fieldType = LongConfigurationField.class)
	private Long potionEffectMaxDuration = Long.valueOf(TimeUnit.MINUTES.toSeconds(10) * 20);
	@ConfigurationFieldDefinition(fieldName = "potion.effects.max_count", fieldType = IntegerConfigurationField.class)
	private Integer potionEffectMaxCount = Integer.valueOf(4);

	@ConfigurationFieldDefinition(fieldName = "firework_effect.colors.max_count", fieldType = IntegerConfigurationField.class)
	private Integer fireworkEffectColorsMaxCount = Integer.valueOf(16);

	@ConfigurationFieldDefinition(fieldName = "firework.max_power", fieldType = IntegerConfigurationField.class)
	private Integer fireworkMaxPower = Integer.valueOf(64);
	@ConfigurationFieldDefinition(fieldName = "firework.max_effects", fieldType = IntegerConfigurationField.class)
	private Integer fireworkMaxEffects = Integer.valueOf(8);

	@ConfigurationFieldDefinition(fieldName = "knowledge_book.max_recipes", fieldType = IntegerConfigurationField.class)
	private Integer knowledgeBookMaxRecipes = Integer.valueOf(10);


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
		return enchantmentBookMaxLevel;
	}

	public int getEnchantmentBookMaxCount() {
		return enchantmentBookMaxCount;
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
		return potionEffectMaxAmplifier;
	}

	public long getPotionEffectsMaxDuration() {
		return potionEffectMaxDuration;
	}

	public int getPotionEffectsMaxCount() {
		return potionEffectMaxCount;
	}


	public int getFireworkEffectColorsMaxCount() {
		return fireworkEffectColorsMaxCount;
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

}
