package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * typeahead javascript library
 */
public class TypeaheadJsReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final TypeaheadJsReference INSTANCE = new TypeaheadJsReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static TypeaheadJsReference instance() {
        return Holder.INSTANCE;
    }

    private TypeaheadJsReference() {
        super("typeaheadjs/current/typeahead.bundle.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = new ArrayList<>();

        dependencies.add(JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));
        return dependencies;
    }
}
