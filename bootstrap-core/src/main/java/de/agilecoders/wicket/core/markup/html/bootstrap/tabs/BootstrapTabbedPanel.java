package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;

/**
 * Styled version of {@link TabbedPanel}.
 *
 * @author miha
 */
public class BootstrapTabbedPanel<T extends ITab> extends TabbedPanel<T> {
    private static final long serialVersionUID = 1L;

    public BootstrapTabbedPanel(String id, List<T> tabs) {
        this(id, tabs, null);
    }

    public BootstrapTabbedPanel(String id, List<T> tabs, IModel<Integer> model) {
        super(id, tabs, model);

        BootstrapBaseBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
    }

    @Override
    protected String getSelectedTabCssClass() {
        return "active";
    }

    @Override
    protected String getLastTabCssClass() {
        return "";
    }

	@Override
	protected String getTabContainerCssClass()
	{
		return "nav nav-tabs";
	}

    protected WebMarkupContainer addSelectedTabBehavior(WebMarkupContainer link, int index) {
        link.add(new Behavior() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onComponentTag(Component component, ComponentTag tag) {
                super.onComponentTag(component, tag);

                if (index == getSelectedTab()) {
                    tag.append("class", getSelectedTabCssClass(), " ");
                }
            }

            @Override
            public boolean isTemporary(Component component) {
                return true;
            }
        });
        return link;
    }

    @Override
    protected WebMarkupContainer newLink(String linkId, int index) {
        final WebMarkupContainer link = super.newLink(linkId, index);
        return addSelectedTabBehavior(link, index);
    }
}
