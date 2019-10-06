package de.agilecoders.wicket.less;

import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

class LessResourceTest extends Assertions {

    /**
     * Tests the compilation of Less resources to Css.
     * Makes a request to Less resource and asserts that an expected Css
     * content is being returned.
     * The Less resource imports another one via "@import 'some.less'"
     */
    @Test
    void request() throws IOException {

        WicketTester tester = new WicketTester(new TestApplication());
        tester.startResourceReference(new LessResourceReference(HomePage.class, "resources/root.less"));
        CharSequence cssContent = tester.getLastResponseAsString();
        cssContent = Strings.replaceAll(cssContent, "\r", "");

        InputStream expectedInputStream = LessResourceTest.class.getResourceAsStream("resources/expected.css");
        CharSequence expected = IOUtils.toString(expectedInputStream);
        expected = Strings.replaceAll(expected, "\r", "");
        assertEquals(expected, cssContent);
    }
}
