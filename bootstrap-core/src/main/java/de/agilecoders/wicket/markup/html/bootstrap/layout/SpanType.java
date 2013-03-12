package de.agilecoders.wicket.markup.html.bootstrap.layout;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * References all available spans.
 */
public enum SpanType implements ICssClassNameProvider {

    SPAN1, SPAN2, SPAN3, SPAN4,
    SPAN5, SPAN6, SPAN7, SPAN8,
    SPAN9, SPAN10, SPAN11, SPAN12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private SpanType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }

}