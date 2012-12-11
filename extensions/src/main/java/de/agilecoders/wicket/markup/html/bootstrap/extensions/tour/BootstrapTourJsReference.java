package de.agilecoders.wicket.markup.html.bootstrap.extensions.tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.agilecoders.wicket.markup.html.bootstrap.extensions.references.BootstrapDatepickerJsReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.references.JQueryCookieJsReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A JavaScript resource reference that contributes the bootstrap-tour.js and
 * its dependencies.
 */
public class BootstrapTourJsReference extends JavaScriptResourceReference
{
    public static final BootstrapTourJsReference INSTANCE = new BootstrapTourJsReference();

    public BootstrapTourJsReference() {
        this(null, null, null);
    }

    public BootstrapTourJsReference(Locale locale, String style, String variation)
    {
        super(BootstrapTourJsReference.class, "js/bootstrap-tour.js", locale, style, variation);
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies()
    {
        List<HeaderItem> dependencies = new ArrayList<HeaderItem>();
        for (HeaderItem dep : super.getDependencies()) {
            dependencies.add(dep);
        }
        dependencies.add(JavaScriptHeaderItem.forReference(JQueryCookieJsReference.INSTANCE));
        dependencies.add(JavaScriptHeaderItem.forReference(BootstrapDatepickerJsReference.INSTANCE));
        return dependencies;
    }
}
