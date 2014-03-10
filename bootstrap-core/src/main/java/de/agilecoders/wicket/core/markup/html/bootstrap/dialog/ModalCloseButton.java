package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

/**
 * A simple close button for the {@link Modal} component.
 *
 * @author miha
 * @deprecated Please use {@link BootstrapModalCloseButton} because it does not trigger a server round trip.
 */
public class ModalCloseButton extends Link<String> {

    private final ButtonBehavior buttonBehavior;

    /**
     * Construct.
     */
    public ModalCloseButton() {
        this(Model.of("Close"));
    }

    /**
     * Construct.
     *
     * @param label The button label
     */
    public ModalCloseButton(final IModel<String> label) {
        super("button", label);

        setBody(getDefaultModel());
        buttonBehavior = new ButtonBehavior(Buttons.Type.Default);

        add(new AttributeModifier("data-dismiss", "modal"));
        add(buttonBehavior);
    }

    /**
     * sets the button type
     *
     * @param type The button type to use
     * @return this instance for chaining
     */
    public ModalCloseButton type(final Buttons.Type type) {
        buttonBehavior.setType(type);
        return this;
    }

    /**
     * sets the button size
     *
     * @param size The button size
     * @return this instance for chaining
     */
    public ModalCloseButton size(final Buttons.Size size) {
        buttonBehavior.setSize(size);
        return this;
    }

    @Override
    public void onClick() {
        // nothing to do here;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        IModal modal = findParent(IModal.class);
        if(modal != null) {
        	setAnchor(modal.getModal());
        }
    }
}
