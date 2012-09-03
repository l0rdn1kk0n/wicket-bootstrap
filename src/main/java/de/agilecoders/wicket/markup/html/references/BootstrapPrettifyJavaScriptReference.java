package de.agilecoders.wicket.markup.html.references;

import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.Bootstrap;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapPrettifyJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new BootstrapPrettifyJavaScriptReference();

    /**
     * Private constructor.
     */
    private BootstrapPrettifyJavaScriptReference() {
        super(BootstrapPrettifyJavaScriptReference.class, "js/prettify.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));

        return dependencies;
    }
}