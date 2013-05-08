/**
 * Copyright 2012 Muzima Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mclinic.api.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.search.api.module.SearchModule;
import com.mclinic.util.Constants;

import java.io.IOException;
import java.util.Properties;

/**
 * TODO: Write brief description about the class here.
 */
public class ContextFactory {

    private static final String OPENMRS_UUID = "uuid";

    private static Properties contextProperties = new Properties();

    static {
        contextProperties.setProperty(
                Constants.LUCENE_DIRECTORY_NAME, System.getProperty("java.io.tmpdir"));
        contextProperties.setProperty(Constants.LUCENE_DOCUMENT_KEY, OPENMRS_UUID);
    }

    /**
     * Set a property's value for the context property.
     *
     * @param property      the property name.
     * @param propertyValue the property value.
     */
    public static void setProperty(final String property, final String propertyValue) {
        contextProperties.setProperty(property, propertyValue);
    }

    /**
     * Set the context's properties to the properties object.
     *
     * @param properties the properties to be set as the context's properties.
     */
    public static void setProperties(final Properties properties) {
        contextProperties = properties;
    }

    /**
     * Get copy of the properties with the current properties as the default value. Changing values in the returned
     * properties will not change the actual values stored inside the context properties. Use the
     * <code>setProperty</code> or <code>setProperties</code> instead.
     *
     * @return copy of the properties with the values from the context's properties as the default.
     */
    public static Properties getProperties() {
        return new Properties(contextProperties);
    }

    // TODO: create context with different signature
    // potential signatures are:
    // * createContext(String path) --> path to the lucene location
    // * createContext(Module ... modules) --> if the consumer wants to create different modules.
    public static Context createContext() throws IOException {
        Module searchModule = new SearchModule();
        Module muzimaModule = new MuzimaModule(
                contextProperties.getProperty(Constants.LUCENE_DIRECTORY_NAME),
                contextProperties.getProperty(Constants.LUCENE_DOCUMENT_KEY));
        Module module = Modules.combine(searchModule, muzimaModule);
        Injector injector = Guice.createInjector(module);
        return new Context(injector);
    }
}
