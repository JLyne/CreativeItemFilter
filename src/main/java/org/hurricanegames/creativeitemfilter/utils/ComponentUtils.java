package org.hurricanegames.creativeitemfilter.utils;

import net.kyori.adventure.text.*;
import org.hurricanegames.creativeitemfilter.CreativeItemFilter;

import java.util.List;
import java.util.logging.Logger;

public final class ComponentUtils {
	private static final ComponentValidator VALIDATOR = new ComponentValidator();

	public static boolean validateComponent(Component component, int maxLength) {
		return VALIDATOR.validate(component, maxLength);
	}

	private static class ComponentValidator {
		private static final List<Class<? extends Component>> SAFE_TYPES = List.of(
			TextComponent.class,
			TranslatableComponent.class,
			KeybindComponent.class);

		private int maxLength = 0;
		private int childDepth = 0;
		private int argumentDepth = 0;
		private int contentLength = 0;
		private int childCount = 0;

		private ComponentValidator() {}

		public boolean validate(Component component, int maxLength) {
			childDepth = 0;
			argumentDepth = 0;
			contentLength = 0;
			childCount = 0;
			this.maxLength = maxLength;

			return validateComponent(component);
		}

		private boolean validateComponent(Component component) {
			Logger logger = CreativeItemFilter.getInstance().getLogger();

			//Exceeded maximum depth
			if(childDepth > CreativeItemFilter.configuration.getComponentMaxChildDepth()) {
				logger.fine("Component validation failed: Over child depth limit");
				return false;
			}

			//Exceeded maximum argument depth
			if(argumentDepth > CreativeItemFilter.configuration.getComponentMaxTranslationDepth()) {
				logger.fine("Component validation failed: Over argument depth limit");
				return false;
			}

			// Disallow types things that shouldn't be on items
			if(SAFE_TYPES.stream().noneMatch(c -> c.isAssignableFrom(component.getClass()))) {
				logger.fine("Component validation failed: Bad component type");
				return false;
			}

			// Disallow events which shouldn't be on items
			if(component.clickEvent() != null || component.hoverEvent() != null) {
				logger.fine("Component validation failed: Has bad event");
				return false;
			}

			// Check translation arguments
			if(component instanceof TranslatableComponent translatable) {
				//Exceeded maximum arguments
				if(translatable.arguments().size() >
						CreativeItemFilter.configuration.getComponentMaxTranslationArguments()) {
					return false;
				}

				for(TranslationArgument argument : translatable.arguments()) {
					if(!(argument.value() instanceof Component componentArg)) {
						continue;
					}

					argumentDepth++;
					if(!validateComponent(componentArg)) {
						return false;
					}
					argumentDepth--;
				}
			}

			if(component instanceof TextComponent text) {
				contentLength += text.content().length();

				if(contentLength > maxLength) {
					logger.fine("Component validation failed: Over content length limit");
					return false;
				}
			}

			childCount += component.children().size();

			if(childCount > CreativeItemFilter.configuration.getComponentMaxChildCount()) {
				logger.fine("Component validation failed: Over child count limit");
				return false;
			}

			// Check children
			for(Component child : component.children()) {
				childDepth++;
				if(!validateComponent(child)) {
					return false;
				}
				childDepth--;
			}

			return true;
		}
	}
}
