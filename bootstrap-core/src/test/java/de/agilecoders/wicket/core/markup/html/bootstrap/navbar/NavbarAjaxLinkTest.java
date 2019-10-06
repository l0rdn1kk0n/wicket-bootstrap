package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link NavbarAjaxLink} component
 *
 * @author miha
 */
public class NavbarAjaxLinkTest extends WicketApplicationTest {

    @Override
    protected void init(WebApplication app) {
        super.init(app);

        app.getMarkupSettings().setStripWicketTags(true);
    }

    @Test
    void splitterIsVisibleIfIconIs() {
        startComponentInPage(new NavbarAjaxLink<String>(id(), Model.of("label")) {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
            }
        }.setIconType(new IconType("test-icon") {
            @Override
            public String cssClassName() {
                return "test-icon";
            }
        }));

        tester().assertVisible("id:splitter");
        tester().assertContains("</i>&nbsp;label");
    }

    @Test
    void splitterIsHiddenIfIconIs() {
        startComponentInPage(new NavbarAjaxLink<String>(id(), Model.of("label")) {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
            }
        }.setIconType(null));

        tester().assertInvisible("id:splitter");
        tester().assertContainsNot("&nbsp;label");
    }
}
