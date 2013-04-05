package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.CssClassNames;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.util.lang.Args;

/**
 * Helper class that holds all special style modification
 * types of a button.
 *
 * @author miha
 */
public final class Buttons {

    /**
     * HACK issue #79: wicket changes tag name if component wasn't enabled
     *
     * @param component the component to fix
     * @param tag the component tag
     */
    public static void fixDisabledState(Component component, ComponentTag tag) {
        if (!component.isEnabled()) {
            if (component instanceof AbstractLink) {
                tag.setName("a");
            } else if (component instanceof Button) {
                tag.setName("button");
            } else {
                if (tag.getAttribute("value") != null) {
                    tag.setName("input");
                } else {
                    tag.setName("button");
                }
            }

            tag.put("disabled", "disabled");
        }
    }

    /**
     * defines all possible sizes of a button element.
     */
    public static enum Size implements ICssClassNameProvider {
        Mini("btn-mini"),
        Small("btn-small"),
        Medium(""),
        Large("btn-large");

        private final String cssClassName;

        /**
         * Construct.
         *
         * @param cssClassName the css class name of this size
         */
        private Size(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        @Override
        public String cssClassName() {
            return cssClassName;
        }

    }

    /**
     * Make a set of buttons appear vertically or horizontally stacked.
     */
    public static enum Orientation implements ICssClassNameProvider {
        Horizontal,
        Vertical;

        @Override
        public String cssClassName() {
            return equals(Horizontal) ? "" : "btn-group-" + name().toLowerCase();
        }
    }

    /**
     * defines all possible button types.
     */
    public static enum Type implements ICssClassNameProvider {
        Default(""), // Standard gray button with gradient
        Menu(""), // Menu button which has no default css class name
        Primary("btn-primary"), // Provides extra visual weight and identifies the primary action in a set of buttons
        Info("btn-info"), // Used as an alternate to the default styles
        Success("btn-success"), // Indicates a successful or positive action
        Warning("btn-warning"), // Indicates caution should be taken with this action
        Danger("btn-danger"), // Indicates a dangerous or potentially negative action
        Link("btn-link"); // Deemphasize a button by making it look like a link while maintaining button behavior

        private final String cssClassName;

        /**
         * Construct.
         *
         * @param cssClassName the css class name of button type
         */
        private Type(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        /**
         * @return css class name of button type
         */
        @Override
        public String cssClassName() {
            return cssClassName;
        }

    }

    /**
     * helper method that adds all necessary css styles to given component tag.
     *
     * @param component the component to which given tag belongs, needed because of enabled state
     * @param tag the component tag
     * @param classNameProviders all css class names to add
     */
    public static void onComponentTag(final Component component, final ComponentTag tag, final ICssClassNameProvider... classNameProviders) {
        Args.notNull(classNameProviders, "classNameProviders");

        final CssClassNames.Builder builder = CssClassNames.newBuilder().add(
                "btn", (component.isEnabled() ? "" : "btn-disabled"));

        for (final ICssClassNameProvider provider : classNameProviders) {
            builder.add(provider.cssClassName());
        }

        Attributes.addClass(tag, builder.asString());
    }

    /**
     * private constructor to prevent instantiation
     */
    private Buttons() {
        throw new UnsupportedOperationException();
    }
}
