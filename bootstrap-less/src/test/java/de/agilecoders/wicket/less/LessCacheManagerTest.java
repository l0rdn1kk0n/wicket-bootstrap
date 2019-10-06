package de.agilecoders.wicket.less;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.io.Connections;
import org.apache.wicket.util.time.Time;

import com.github.sommeri.less4j.LessCompiler.Configuration;
import com.github.sommeri.less4j.LessSource;
import com.github.sommeri.less4j.LessSource.URLSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LessCacheManagerTest {

    private int invocationOfGetContent;
    private int invocationOfNewConfiguration;

    @BeforeEach
    void setUp() {
        invocationOfGetContent = 0;
        invocationOfNewConfiguration = 0;
    }

    /**
     * Create a URLSource that keeps track of "getContent()" invocations.
     */
    URLSource createSampleURLSource() {
        return new LessSource.URLSource(getClass().getResource("resources/root.less")) {
            @Override
            public String getContent() throws FileNotFound, CannotReadFile {
                invocationOfGetContent++;
                return super.getContent();
            }
        };
    }

    @Test
    void cachesCssResult() {
        LessCacheManager cacheManager = new LessCacheManager();

        URLSource urlSource = createSampleURLSource();

        // LessSource.getContent() ist only necessary, when compiling the .less file.
        // Otherwise the result would be in the cache.

        cacheManager.getCss(urlSource);
        assertEquals(1, invocationOfGetContent);

        cacheManager.getCss(urlSource);
        assertEquals(1, invocationOfGetContent);
    }

    @Test
    void clearCacheForcesRecompile() {
        LessCacheManager cacheManager = new LessCacheManager();

        URLSource urlSource = createSampleURLSource();

        // LessSource.getContent() ist only necessary, when compiling the .less file.
        // Otherwise the result would be in the cache.

        cacheManager.getCss(urlSource);
        assertEquals(1, invocationOfGetContent);

        cacheManager.clearCache();

        cacheManager.getCss(urlSource);
        assertEquals(2, invocationOfGetContent);
    }

    @Test
    void usesLessCompilerConfigurationFactoryProvidedToCreateANewLesCompilerConfiguration() {
        LessCacheManager cacheManager = new LessCacheManager(() -> {
            invocationOfNewConfiguration++;
            return new Configuration();
        });

        URLSource urlSource = createSampleURLSource();

        cacheManager.getCss(urlSource);
        assertEquals(1, invocationOfNewConfiguration);

        cacheManager.clearCache();

        cacheManager.getCss(urlSource);
        assertEquals(2, invocationOfNewConfiguration);
    }

    @Test
    void usesDefaultConfigurationFactoryWhenProvidingNull() {
        LessCacheManager cacheManager = new LessCacheManager(null);

        URLSource urlSource = createSampleURLSource();

        cacheManager.getCss(urlSource);

        // no NullPointerException
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void timestampBeforeAndAfterCompile() throws IOException
    {
        long currentTimeMillis = System.currentTimeMillis();
        LessCacheManager cacheManager = new LessCacheManager();

        URL parentUrl = getClass().getResource("resources/timeParent.less");
        File parentFile = Files.getLocalFileFromUrl(parentUrl);
        parentFile.setLastModified(currentTimeMillis);
        Time expectedTimeBeforeCompile = Connections.getLastModified(parentUrl);
        URLSource urlSource = new LessSource.URLSource(parentUrl);

        // Make sure that the imported file time stamp is newer than timeRoots
        URL childUrl = getClass().getResource("resources/timeChild.less");
        File childFile = Files.getLocalFileFromUrl(childUrl);
        childFile.setLastModified(currentTimeMillis + 60000);
        Time expectedTimeAfterCompile = Connections.getLastModified(childUrl);

        assertEquals(expectedTimeBeforeCompile, cacheManager.getLastModifiedTime(urlSource));

        cacheManager.getCss(urlSource);
        assertEquals(expectedTimeAfterCompile, cacheManager.getLastModifiedTime(urlSource));
    }

    @Test
    void clearCacheCreatesNewUrlSource()
    {
        LessCacheManager cacheManager = new LessCacheManager();

        URL resourceUrl = getClass().getResource("resources/root.less");
        String scopeClass = getClass().getName();
        URLSource urlSourceBefore = cacheManager.getLessSource(resourceUrl, scopeClass);
        cacheManager.clearCache();
        URLSource urlSourceAfter = cacheManager.getLessSource(resourceUrl, scopeClass);

        assertNotSame(urlSourceBefore, urlSourceAfter, "We should get different instances of URLSource");
    }

    @Test
    void clearCacheImportedSourcesSize()
    {
        LessCacheManager cacheManager = new LessCacheManager();

        URL resourceUrl = getClass().getResource("resources/root.less");
        String scopeClass = getClass().getName();
        URLSource urlSource = cacheManager.getLessSource(resourceUrl, scopeClass);

        cacheManager.getCss(urlSource);
        int expectedItems = urlSource.getImportedSources().size();

        cacheManager.clearCache();
        urlSource = cacheManager.getLessSource(resourceUrl, scopeClass);

        cacheManager.getCss(urlSource);
        assertEquals(expectedItems, urlSource.getImportedSources().size());
    }
}
