package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for ProgressBar
 */
class ProgressBarTest extends WicketApplicationTest {

    @Test
    void progressBarWithoutModelHasNoDefaultStack() {
        ProgressBar progressBar = new ProgressBar(id());

        RepeatingView stacks = (RepeatingView) progressBar.get("stacks");
        assertNotNull(stacks, "The progress bar has no repeater for the stacks!");
        assertFalse(stacks.iterator().hasNext(), "The progress bar should not have default stack");
    }

    @Test
    void progressBarWithModelHasADefaultStack() {
        ProgressBar progressBar = new ProgressBar(id(), Model.of(23));

        RepeatingView stacks = (RepeatingView) progressBar.get("stacks");
        assertNotNull(stacks, "The progress bar has no repeater for the stacks!");
        assertTrue(stacks.iterator().hasNext(), "The progress bar must have default stack");
    }

    @Test
    void animatedBarIsAlwaysStripped() {
        ProgressBar progressBar = new ProgressBar(id());
        progressBar.active(true);

        assertTrue(progressBar.striped());
    }

    @Test
    void progressBarWithDefaultStackMarkup() {
        tester().getApplication().getMarkupSettings().setStripWicketTags(true);
        String markupId = "progressBar";
        int progress = 23;
        ProgressBar progressBar = new ProgressBar(id(), Model.of(progress), ProgressBar.Type.DANGER);
        progressBar.setMarkupId(markupId);
        progressBar.active(true);

        startComponentInPage(progressBar);
//        System.err.println("RES:\n" + tester().getLastResponseAsString());
        TagTester progressBarTester = tester().getTagById(markupId);
        assertEquals("progress active progress-striped", progressBarTester.getAttribute("class"));
        TagTester stackTester = progressBarTester.getChild("class", "progress-bar progress-bar-danger");
        assertEquals("progressbar", stackTester.getAttribute("role"));
        assertEquals("width: "+progress+"%", stackTester.getAttribute("style"));
        assertEquals("" + progress, stackTester.getAttribute("aria-valuenow"));
        assertEquals("" + ProgressBar.MIN, stackTester.getAttribute("aria-valuemin"));
        assertEquals("" + ProgressBar.MAX, stackTester.getAttribute("aria-valuemax"));

        TagTester stackLabelTester = stackTester.getChild("class", "sr-only");
        assertEquals("" + progress + "%", stackLabelTester.getValue());
    }

}
