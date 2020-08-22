package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Args;

import java.util.List;

/**
 * Wrap a series of buttons with .btn in .btn-group.
 *
 * @author miha
 */
public abstract class ButtonGroup extends Panel {
    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    public enum Size implements ICssClassNameProvider {
        Small("sm"), Default(""), Large("lg");

        private final String cssName;

        Size(String cssName) {
            this.cssName = cssName;
        }

        public String cssClassName() {
            return "btn-group-" +cssName;
        }
    }

    private final Buttons.Orientation orientation;

    private final Size size;

    /**
     * Construct.
     *
     * @param markupId The markup id.
     */
    public ButtonGroup(final String markupId) {
        this(markupId, Buttons.Orientation.Horizontal, Size.Default);
    }

    /**
     * Construct.
     *
     * @param markupId    The markup id.
     * @param orientation Make a set of buttons appear vertically stacked rather than horizontally if set to {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Orientation#Vertical}.
     */
    public ButtonGroup(final String markupId, final Buttons.Orientation orientation) {
        this(markupId, orientation, Size.Default);
    }

    public ButtonGroup(final String markupId, final Buttons.Orientation orientation, Size size) {
        super(markupId);

        Args.notNull(orientation, "orientation");

        this.orientation = orientation;
        this.size = size;

        BootstrapBaseBehavior.addTo(this);
    }

    /**
     * @see org.apache.wicket.Component#onInitialize()
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(newButtonList("buttons"));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Attributes.addClass(tag, orientation.cssClassName());

        if (!Size.Default.equals(size)) {
            Attributes.addClass(tag, size.cssClassName());
        }
    }

    /**
     * creates a new {@link ButtonList} that contains all buttons from {@link #newButtons(String)}
     *
     * @param markupId the markup id of {@link ButtonList}
     * @return new {@link ButtonList} instance
     */
    protected ButtonList newButtonList(final String markupId) {
        final ButtonList buttonList = new ButtonList(markupId, newButtons(ButtonList.getButtonMarkupId()));
        buttonList.setRenderBodyOnly(true);

        return buttonList;
    }

    /**
     * creates a list of sub menu buttons which will be shown as group.
     *
     * @param buttonMarkupId the markup id that all buttons must be use.
     * @return list of buttons
     */
    protected abstract List<AbstractLink> newButtons(final String buttonMarkupId);

}
