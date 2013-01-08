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
package com.mclinic.api.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.mclinic.api.registry.ServerConfigRegistry;
import com.mclinic.search.api.logger.LogLevel;
import com.mclinic.util.Constants;

public class MuzimaModule extends AbstractModule {

    private String lucenePath;

    private String documentKey;

    private String server;

    private String username;

    private String password;

    public MuzimaModule(final String lucenePath, final String documentKey) {
        super();
        this.lucenePath = lucenePath;
        this.documentKey = documentKey;
    }

    /**
     * @param server the web server to set
     */
    public void setServer(final String server) {
        this.server = server;
    }

    /**
     * @param username the user to set
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named(Constants.LUCENE_DIRECTORY_NAME)).toInstance(lucenePath);
        bind(String.class).annotatedWith(Names.named(Constants.LUCENE_DOCUMENT_KEY)).toInstance(documentKey);
        bind(LogLevel.class).toInstance(LogLevel.DEBUG);

        // if instance needs a server then register the server username and password
        if (server != null) {
            ServerConfigRegistry registry = new ServerConfigRegistry();
            registry.putEntry(Constants.SERVER, server);
            registry.putEntry(Constants.CONNECTION_USERNAME, username);
            registry.putEntry(Constants.CONNECTION_PASSWORD, password);
            bind(ServerConfigRegistry.class).toInstance(registry);
        }

    }
}
