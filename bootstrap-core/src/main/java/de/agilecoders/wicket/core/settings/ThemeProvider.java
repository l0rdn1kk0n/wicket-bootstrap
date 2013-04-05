package de.agilecoders.wicket.core.settings;

import java.util.List;

/**
 * The {@code ThemeProvider} interface
 *
 * @author miha
 */
public interface ThemeProvider {

    /**
     * returns a theme by its name. If
     *
     * @param name The name of the theme
     * @return the theme according to given name
     */
    ITheme byName(final String name);

    /**
     * @return a list of all available themes
     */
    List<ITheme> available();

    /**
     * @return the default theme
     */
    ITheme defaultTheme();
}
