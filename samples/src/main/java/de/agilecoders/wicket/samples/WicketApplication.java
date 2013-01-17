package de.agilecoders.wicket.samples;

import com.google.javascript.jscomp.CompilationLevel;
import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.javascript.GoogleClosureJavaScriptCompressor;
import de.agilecoders.wicket.javascript.YuiCssCompressor;
import de.agilecoders.wicket.markup.html.RenderJavaScriptToFooterHeaderResponseDecorator;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5PlayerCssReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5PlayerJavaScriptReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.icon.OpenWebIconsCssReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.jqueryui.JQueryUIJavaScriptReference;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyCssReference;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyJavaScriptReference;
import de.agilecoders.wicket.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.markup.html.themes.metro.MetroTheme;
import de.agilecoders.wicket.samples.assets.base.ApplicationJavaScript;
import de.agilecoders.wicket.samples.assets.base.FixBootstrapStylesCssResourceReference;
import de.agilecoders.wicket.samples.pages.HomePage;
import de.agilecoders.wicket.settings.BootstrapSettings;
import de.agilecoders.wicket.settings.BootswatchThemeProvider;
import de.agilecoders.wicket.settings.ThemeProvider;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.MessageDigestResourceVersion;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import java.io.IOException;
import java.util.Properties;

/**
 * Demo Application instance.
 */
public class WicketApplication extends WebApplication {
    private Properties properties;

    /**
     * Get Application for current thread.
     *
     * @return The current thread's Application
     */
    public static WicketApplication get() {
        return (WicketApplication) Application.get();
    }

    /**
     * Constructor.
     */
    public WicketApplication() {
        super();

        properties = loadProperties();
        setConfigurationType(RuntimeConfigurationType.valueOf(properties.getProperty("configuration.type")));
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        // wicket markup leads to strange ui problems because css selectors
        // won't match anymore.
        getMarkupSettings().setStripWicketTags(true);

        // deactivate ajax debug mode
        getDebugSettings().setAjaxDebugModeEnabled(false);

        configureBootstrap();
        configureResourceBundles();

        optimizeForWebPerformance();

        new AnnotatedMountScanner().scanPackage("de.agilecoders.wicket.samples.pages").mount(this);
        //StaticResourceRewriteMapper.withBaseUrl("http://wb.agile-coders.de").install(this);
    }

    /**
     * optimize wicket for a better web performance
     */
    private void optimizeForWebPerformance() {
        if (usesDeploymentConfig()) {
            getResourceSettings().setCachingStrategy(new FilenameWithVersionResourceCachingStrategy(
                    new MessageDigestResourceVersion()
            ));

            getResourceSettings().setJavaScriptCompressor(new GoogleClosureJavaScriptCompressor(CompilationLevel.SIMPLE_OPTIMIZATIONS));
            getResourceSettings().setCssCompressor(new YuiCssCompressor());

            getFrameworkSettings().setSerializer(new DeflatedJavaSerializer(getApplicationKey()));
        }

        setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator());
    }

    /**
     * configure all resource bundles (css and js)
     */
    private void configureResourceBundles() {
        getResourceBundles().addJavaScriptBundle(WicketApplication.class, "core.js",
                                                 (JavaScriptResourceReference) getJavaScriptLibrarySettings().getJQueryReference(),
                                                 (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketEventReference(),
                                                 (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketAjaxReference(),
                                                 (JavaScriptResourceReference) ModernizrJavaScriptReference.INSTANCE
        );

        getResourceBundles().addJavaScriptBundle(WicketApplication.class, "bootstrap.js",
                                                 (JavaScriptResourceReference) Bootstrap.getSettings().getJsResourceReference(),
                                                 (JavaScriptResourceReference) Bootstrap.getSettings().getJqueryPPResourceReference(),
                                                 (JavaScriptResourceReference) BootstrapPrettifyJavaScriptReference.INSTANCE,
                                                 ApplicationJavaScript.INSTANCE
        );

        getResourceBundles().addJavaScriptBundle(WicketApplication.class, "bootstrap-extensions.js",
                                                 JQueryUIJavaScriptReference.instance(),
                                                 Html5PlayerJavaScriptReference.instance()
        );

        getResourceBundles().addCssBundle(WicketApplication.class, "bootstrap-extensions.css",
                                          Html5PlayerCssReference.instance(),
                                          OpenWebIconsCssReference.instance()
        );

        if (Bootstrap.getSettings().useResponsiveCss()) {
            getResourceBundles().addCssBundle(WicketApplication.class, "application.css",
                                              (CssResourceReference) Bootstrap.getSettings().getResponsiveCssResourceReference(),
                                              (CssResourceReference) BootstrapPrettifyCssReference.INSTANCE,
                                              FixBootstrapStylesCssResourceReference.INSTANCE
            );
        } else {
            getResourceBundles().addCssBundle(WicketApplication.class, "application.css",
                                              (CssResourceReference) BootstrapPrettifyCssReference.INSTANCE,
                                              FixBootstrapStylesCssResourceReference.INSTANCE
            );
        }
    }

    /**
     * configures wicket-bootstrap and installs the settings.
     */
    private void configureBootstrap() {
        final BootstrapSettings settings = new BootstrapSettings(this);
        settings.useJqueryPP(false)
                .useModernizr(false)
                .useResponsiveCss(true)
                .setJsResourceFilterName("footer-container");

        //reactivate if new less4j version is available:
        //settings.getBootstrapLessCompilerSettings().setUseLessCompiler(usesDevelopmentConfig());
        //settings.getBootstrapLessCompilerSettings().setLessCompiler(new Less4JCompiler());

        ThemeProvider themeProvider = new BootswatchThemeProvider() {{
            add(new MetroTheme());
            defaultTheme("wicket");
        }};
        settings.setThemeProvider(themeProvider);

        Bootstrap.install(this, settings);
    }

    /**
     * @return used configuration properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * loads all configuration properties from disk
     *
     * @return configuration properties
     */
    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
        return properties;
    }
}
