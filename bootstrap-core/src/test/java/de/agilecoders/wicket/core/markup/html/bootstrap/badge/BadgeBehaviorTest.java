package de.agilecoders.wicket.core.markup.html.bootstrap.badge;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.badge.BadgeBehavior;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link BadgeBehavior}
 *
 * @author miha
 */
class BadgeBehaviorTest extends WicketApplicationTest {

    @Test
    void classNameWasAdded() {
        assertCssClass(new BadgeBehavior(), "badge");
    }

}
