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
public class InputAppendBorder extends InputBorder {

    public InputAppendBorder(String id, IconType iconType) {
        super(id, iconType);
    }

    public InputAppendBorder(String id, IModel<String> label) {
        super(id, label);
    }

    public InputAppendBorder(String id, Component component) {
        super(id, component);
    }

    @Override
    protected String id() {
        return "append";
    }

    @Override
    protected CssClassNameAppender newCssClassNameAppender() {
        return new CssClassNameAppender("input-append");
    }
}
