package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton;

/**
 * Special {@link DropDownButton} for a {@link Navbar}.
 *
 *
 *
 * @author miha
 */
public abstract class NavbarDropDownButton extends DropDownButton {

    /**
     * Construct.
     *
     * @param model the label of this dropdown button
     */
    public NavbarDropDownButton(final IModel<String> model) {
        super(Navbar.COMPONENT_ID, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.setRenderBodyOnly(true);

        // add the dropdown css class name to the parent element not button element itself.
        this.getParent().add(new CssClassNameAppender("dropdown"));
    }

    @Override
    protected final void addButtonBehavior(final IModel<Buttons.Type> buttonType, final IModel<Buttons.Size> buttonSize) {
        // do nothing, because navbar dropdown button inherits its styles from navbar.
    }

}
