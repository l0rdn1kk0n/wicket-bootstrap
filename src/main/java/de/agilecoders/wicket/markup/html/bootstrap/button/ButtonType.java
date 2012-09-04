package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */

public enum ButtonType {
    Default(""), // Standard gray button with gradient
    Menu(""), // Menu button which has no default css class name
    Primary("btn-primary"), // Provides extra visual weight and identifies the primary action in a set of buttons
    Info("btn-info"), // Used as an alternate to the default styles
    Success("btn-success"), // Indicates a successful or positive action
    Warning("btn-warning"), // Indicates caution should be taken with this action
    Danger("btn-danger"), // Indicates a dangerous or potentially negative action
    Link("btn-link"); // Deemphasize a button by making it look like a link while maintaining button behavior

    private final String cssClassName;

    private ButtonType(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    String cssClassName() {
        return cssClassName;
    }

    public IModel<ButtonType> asModel() {
        return Model.of(this);
    }
}