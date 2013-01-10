package de.agilecoders.wicket.less;

import com.asual.lesscss.LessEngine;
import com.asual.lesscss.LessException;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import de.agilecoders.wicket.Bootstrap;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.lang.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * An {@link IBootstrapLessCompiler} implementation that loads all less files and their
 * imports and compiles them to an optimized css stream.
 *
 * @author miha
 */
public class LessJsLessCompiler extends AbstractLessCompiler {
    private static final Logger LOG = LoggerFactory.getLogger(LessJsLessCompiler.class);

    /**
     * Use http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * to load LessEngine if it is really needed
     */
    private static class LessEngineHolder {
        private final static LessEngine instance = new LessEngine(Bootstrap.getSettings().getBootstrapLessCompilerSettings().getLessOptions());
    }

    /**
     * @return current less engine
     */
    private static LessEngine getLessEngine() {
        return LessEngineHolder.instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICompiledResource compile(final ILessResource lessResource) {
        return compile(collect(lessResource));
    }

    /**
     * calls the underlying less compiler (less.js/Rhino) and compiles the less
     * content.
     *
     * @param lessFile the {@link ICombinedLessResource}
     * @return generated css as byte array
     */
    protected ICompiledResource compile(ICombinedLessResource lessFile) {
        return new AbstractCompiledResource(lessFile) {
            @Override
            protected byte[] compile(final ICombinedLessResource lessFile) {
                final long start = System.currentTimeMillis();
                try {
                    return getLessEngine().compile(lessFile.asText()).getBytes(getCharset());
                } catch (LessException e) {
                    return handleException(e);
                } finally {
                    LOG.debug("duration of less compiler for {}: {} ms", lessFile.getName(), System.currentTimeMillis() - start);
                }
            }
        };
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    private byte[] handleException(LessException e) {
        String filename = e.getFilename();
        List<String> extractList = e.getExtract();

        String extract = null;
        if (extractList != null) {
            extract = Joiner.on(",").join(extractList);
        }

        // Try to detect missing imports
        if (Strings.isNullOrEmpty(extract)) {
            FileNotFoundException fileNotFoundException = Exceptions.findCause(e, FileNotFoundException.class);

            if (fileNotFoundException != null) {
                extract = fileNotFoundException.getMessage();
            }
        }

        throw new WicketRuntimeException("can't parse " + filename + "; error in line " + e.getLine() + " on column " + e.getColumn() + "; " + e.getType() + ": " + extract);
    }

}
