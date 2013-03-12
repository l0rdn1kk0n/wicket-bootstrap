package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class InputPrependBorder extends InputBorder {

    public InputPrependBorder(String id, IconType iconType) {
        super(id, iconType);
    }

    public InputPrependBorder(String id, IModel<String> label) {
        super(id, label);
    }

    public InputPrependBorder(String id, Component component) {
        super(id, component);
    }

    @Override
    protected String id() {
        return "prepend";
    }

    @Override
    protected CssClassNameAppender newCssClassNameAppender() {
        return new CssClassNameAppender("input-prepend");
    }
}
