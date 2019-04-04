package de.agilecoders.wicket.sass;

import de.agilecoders.wicket.webjars.WicketWebjars;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.mock.MockServletContext;
import org.apache.wicket.request.resource.IResourceReferenceFactory;
import org.apache.wicket.request.resource.ResourceReferenceRegistry;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URL;

import io.bit3.jsass.Options;
import io.bit3.jsass.type.SassString;
import io.bit3.jsass.type.SassValue;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * TODO miha: document class purpose
 *
 * @author miha
 */
public class BootstrapSassTest {

    private WicketTester tester;

    @Before
    public void before() {
        tester = new WicketTester(new TestApplication() {
            @Override
            public void init() {
                super.init();

                WicketWebjars.install(this);
                BootstrapSass.install(this);
            }
        });
    }

    @After
    public void after() {
        tester.destroy();
    }

    @Test
    public void importWebContext() throws Exception {
        WebApplication application = tester.getApplication();
        URI uri = getClass().getResource("/de/agilecoders/wicket/sass/test/webContextImported.scss").toURI();
        File file = new File(uri);
        File contextRoot = file.getParentFolder();
        // setup folder /.../bootstrap-sass/src/test/resources/de/agilecoders/wicket/sass/test as a root for the ServletContext
        application.setServletContext(new MockServletContext(application, contextRoot.getAbsolutePath()));

        SassCacheManager sass = SassCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("importWebContext.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, is(equalTo(".rule {\n  background: #999; }\n")));
    }

    /**
     * Tests with {@link ContextRelativeSassResourceReference}
     * <p>
     * https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/524
     *
     * @throws Exception
     */
    @Test
    public void importServletContextRelative() throws Exception {
        WebApplication application = tester.getApplication();
        URI uri = getClass().getResource("/servlet/context/root/").toURI();
        File contextRoot = new File(uri);
        // setup folder /.../bootstrap-sass/src/test/resources/servlet/context/root as a root for the ServletContext
        application.setServletContext(new MockServletContext(application, contextRoot.getAbsolutePath()));

//        tester.startPage(HomePage.class);
        tester.executeUrl("./wicket/resource/org.apache.wicket.Application/relative.scss?--" + ContextRelativeSassResourceReference.CONTEXT_RELATIVE_SASS_REFERENCE_VARIATION);
        tester.assertContains("sass-servlet-relative-cls");
        tester.assertContains("color: #333;");
    }

    @Test
    public void importWebJars() throws Exception {
        SassCacheManager sass = SassCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("import.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, is(equalTo(".rule {\n  background: #007bff; }\n")));
    }

    @Test
    public void importClasspath() {
        SassCacheManager sass = SassCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("importClasspath.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, is(equalTo(".classPathImported {\n  color: #333; }\n")));
    }

    @Test
    public void importPackage() {
        SassCacheManager sass = SassCacheManager.get();
        URL res = BootstrapSassTest.class.getResource("package-dependency-1.scss");

        SassSource sassSource = sass.getSassContext(res, BootstrapSassTest.class.getName());
        String css = sass.getCss(sassSource);
        assertThat(css, containsString("package1"));
        assertThat(css, containsString("package2"));
        assertThat(css, containsString("package3"));
    }

    @Test
    public void importPartials() {
        SassCacheManager sass = SassCacheManager.get();
        URL res = BootstrapSassTest.class.getResource("partial-root.scss");

        SassSource sassSource = sass.getSassContext(res, BootstrapSassTest.class.getName());
        String css = sass.getCss(sassSource);
        assertThat(css, containsString("root-cls"));
        assertThat(css, containsString("partial"));
    }

    @Test
    public void importLocalJarPath() {
        // test case for issue #794
        SassCacheManager sass = SassCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("importRelativeLocalPath.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, containsString("* Bootstrap"));
    }

    @Test
    public void sassResourceReferenceFactoryIsInstalled() {
        ResourceReferenceRegistry registry = tester.getApplication().getResourceReferenceRegistry();
        IResourceReferenceFactory referenceFactory = registry.getResourceReferenceFactory();
        assertThat(referenceFactory, is(instanceOf(SassResourceReferenceFactory.class)));
    }

    @Test
    public void usesCustomSassCompilerConfigurationFactoryWhenProvided() {
        // tests the invocation of a custom sass function that will be registered within the configuration factory
        BootstrapSass.install(Application.get(), () -> {
            Options config = new Options();
            config.getFunctionProviders().add(new TestFunctions());
            return config;
        });

        SassCacheManager sass = SassCacheManager.get();
        URL res = BootstrapSassTest.class.getResource("resources/custom-functions.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, is(".my-class {\n  color: blue; }\n"));
    }

    public static class TestFunctions {

        public SassValue myFancyFunction() {
            return new SassString("blue", false);
        }

    }
}
