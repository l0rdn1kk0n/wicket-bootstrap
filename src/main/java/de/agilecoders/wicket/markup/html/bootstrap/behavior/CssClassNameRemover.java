/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import java.util.List;

/**
 * Removes a value for the CSS class attribute
 */
public class CssClassNameRemover extends CssClassNameAppender {

    /**
     * Creates an AttributeModifier that appends the removeModel's value to the current value of the
     * class attribute, and will add the attribute when it is not there already.
     *
     * @param removeModel the model supplying a single value to append
     */
    public CssClassNameRemover(IModel<String> removeModel) {
        super(removeModel);
    }

    /**
     * Constructor.
     * {@link CssClassNameAppender#CssClassNameAppender(org.apache.wicket.model.IModel)}
     *
     * @param removeValue one or more values to append
     */
    public CssClassNameRemover(String... removeValue) {
        this(Lists.newArrayList(removeValue));
    }

    /**
     * Constructor.
     * {@link CssClassNameAppender#CssClassNameAppender(org.apache.wicket.model.IModel)}
     *
     * @param removeValueList a list of values to append
     */
    public CssClassNameRemover(List<String> removeValueList) {
        this(Model.of(Joiner.on(SEPARATOR).skipNulls().join(removeValueList)));
    }

    /**
     * Constructor.
     * {@link CssClassNameAppender#CssClassNameAppender(org.apache.wicket.model.IModel)}
     *
     * @param cssClassNameProvider a css class name provider
     */
    public CssClassNameRemover(CssClassNameProvider cssClassNameProvider) {
        this(Model.of(cssClassNameProvider.cssClassName()));
    }

    @Override
    protected String newValue(String currentValue, String removeValue) {
        // Short circuit when one of the values is empty: return the other value.
        if (Strings.isEmpty(currentValue)) {
            return removeValue != null ? removeValue : null;
        } else if (Strings.isEmpty(removeValue)) {
            return currentValue != null ? currentValue : null;
        }

        List<String> values = Lists.newArrayList(Splitter.on(separator()).split(currentValue));
        List<String> removeValues = Lists.newArrayList(Splitter.on(separator()).split(removeValue));

        for (String remove : removeValues) {
            values.remove(remove);
        }

        return Joiner.on(separator()).join(values);
    }
}
