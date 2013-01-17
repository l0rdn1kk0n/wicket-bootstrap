package de.agilecoders.wicket.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * Block-level element for quoting content from another source.
 * <p/>
 * <pre>
 *     <blockquote wicket:id="componentId">
 *          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante venenatis.</p>
 *          <small>Someone famous</small>
 *     </blockquote>
 * </pre>
 *
 * @author miha
 */
public abstract class Hero extends WebMarkupContainer {

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Hero(final String componentId) {
        this(componentId, null);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model       the component's model
     */
    public Hero(final String componentId, final IModel<?> model) {
        super(componentId, model);

        add(new HeroBehavior());
    }

}



