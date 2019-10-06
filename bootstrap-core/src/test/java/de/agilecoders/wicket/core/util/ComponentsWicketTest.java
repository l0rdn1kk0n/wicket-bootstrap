package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link Components} class
 *
 * @author miha
 */
class ComponentsWicketTest extends WicketApplicationTest {

    @Test
    void showMakesComponentVisible() {
        Component componentA = new WebMarkupContainer("containerA").setVisible(false);
        Component componentB = new WebMarkupContainer("containerA").setVisible(false);

        Components.show(componentA, componentB);

        assertThat(componentA.isVisible(), is(equalTo(true)));
        assertThat(componentB.isVisible(), is(equalTo(true)));
    }

    @Test
    void componentWillBeInvisibleIfModelIsNotSet() {
        Component componentA = new Label("containerA");

        Components.hideIfModelIsEmpty(componentA);

        assertThat(componentA.isVisible(), is(equalTo(false)));
    }

    @Test
    void componentWillBeInvisibleIfModelIsEmpty() {
        Component componentA = new Label("containerA", Model.of(""));

        Components.hideIfModelIsEmpty(componentA);

        assertThat(componentA.isVisible(), is(equalTo(false)));
    }

    @Test
    void componentWillBeInvisibleIfModelIsNull() {
        Component componentA = new Label("containerA", Model.of((String) null));

        Components.hideIfModelIsEmpty(componentA);

        assertThat(componentA.isVisible(), is(equalTo(false)));
    }
}
