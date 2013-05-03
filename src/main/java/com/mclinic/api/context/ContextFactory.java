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
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.search.api.module.SearchModule;

/**
 * TODO: Write brief description about the class here.
 */
public class ContextFactory {

    // TODO: create context with different signature
    // potential signatures are:
    // * createContext(String path) --> path to the lucene location
    // * createContext(Module ... modules) --> if the consumer wants to create different modules.
    public static Context createContext() {
        String tmpDirectory = System.getProperty("java.io.tmpdir");
        Injector injector = Guice.createInjector(new SearchModule(), new MuzimaModule(tmpDirectory, "uuid"));
        Context context = new Context(injector);
        return context;
    }
}
