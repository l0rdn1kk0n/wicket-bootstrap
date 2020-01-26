package de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A JavaScript resource reference that contributes ladda.js.
 */
public class LaddaJsReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;
    public static final LaddaJsReference INSTANCE = new LaddaJsReference();

    public LaddaJsReference() {
        super(LaddaJsReference.class, "js/ladda.js");
    }
}
