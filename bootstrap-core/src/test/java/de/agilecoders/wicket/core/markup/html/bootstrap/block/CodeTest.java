package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Code} class
 *
 * @author miha
 */
@SuppressWarnings("SpellCheckingInspection")
class CodeTest extends WicketApplicationTest {

    @Test
    void tagNameWasAsserted() {
        assertThrows(MarkupException.class, () -> tester().startComponentInPage(new Code(id())));
    }

    @Test
    void codeTagIsAllowed() {
        tester().startComponentInPage(new Code(id()), createMarkup("code"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

    @Test
    void preTagIsAllowed() {
        tester().startComponentInPage(new Code(id()), createMarkup("pre"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

    @Test
    void linenumberWithStartCssClassNameWasSet() {
        tester().startComponentInPage(new Code(id()).setStartFromLine(5), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "linenums:5");
    }

    @Test
    void linenumberCssClassNameWasSet() {
        tester().startComponentInPage(new Code(id()).setShowLineNumbers(true), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "linenums");
    }

    @Test
    void languageCssClassNameWasSet() {
        tester().startComponentInPage(new Code(id()).setLanguage(CodeBehavior.Language.XHTML), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "lang-xhtml");
    }

    @Test
    void combinationOfCssClassNamesAreSet() {
        tester().startComponentInPage(new Code(id()).setLanguage(CodeBehavior.Language.XHTML).setStartFromLine(5), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "lang-xhtml", "linenums:5", "prettyprint");
    }

    @Test
    void defaultCssClassNamesAreSet() {
        tester().startComponentInPage(new Code(id()), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "prettyprint");
    }

    /**
     * creates default markup for CodeBehavior
     *
     * @param tagName The tag name to use
     * @return code markup
     */
    private Markup createMarkup(final String tagName) {
        return Markup.of("<" + tagName + " wicket:id=\"" + id() + "\">" + "content" + "</" + tagName + ">");
    }

}
