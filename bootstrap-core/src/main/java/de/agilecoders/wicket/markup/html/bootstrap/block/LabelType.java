package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * A LabelType defines the type of label which changes highlighted color.
 *
 * @author miha
 */
public enum LabelType implements ICssClassNameProvider, ICssClassNameModifier {
    Default, Success, Warning, Important, Info, Inverse;

    @Override
    public String cssClassName() {
        return equals(Default) ? "" : name().toLowerCase();
    }

    public String cssClassName(final String prefix) {
        return equals(Default) ? "" : prefix + "-" + name().toLowerCase();
    }

    @Override
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(cssClassName());
    }

    public CssClassNameAppender newCssClassNameModifier(final String prefix) {
        return new CssClassNameAppender(cssClassName(prefix));
    }
}
