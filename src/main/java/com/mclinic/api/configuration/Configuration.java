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
package com.mclinic.api.configuration;

import com.mclinic.search.api.registry.Registry;
import com.mclinic.util.Constants;

/**
 * TODO: move this to model because we need to persist it between use.
 */
public class Configuration {

    private Registry<String, String> registry;

    /**
     * Get the base url of the openmrs server.
     *
     * @return the base url of the openmrs server.
     */
    public String getServer() {
        return registry.getEntryValue(Constants.CONNECTION_SERVER);
    }

    /**
     * Set the base url of the openmrs server.
     *
     * @param server the base url of the openmrs server.
     */
    public void setServer(final String server) {
        registry.putEntry(Constants.CONNECTION_SERVER, server);
    }

    /**
     * Get the username of the openmrs server.
     *
     * @return the username of the openmrs server.
     */
    public String getUsername() {
        return registry.getEntryValue(Constants.CONNECTION_USERNAME);
    }

    /**
     * Set the username of the openmrs server.
     *
     * @param username the username of the openmrs server.
     */
    public void setUsername(final String username) {
        registry.putEntry(Constants.CONNECTION_USERNAME, username);
    }

    /**
     * Set the password of the openmrs server.
     *
     * @return the password of the openmrs server.
     */
    public String getPassword() {
        return registry.getEntryValue(Constants.CONNECTION_PASSWORD);
    }

    /**
     * Set the password of the openmrs server.
     *
     * @param password the password of the openmrs server.
     */
    public void setPassword(final String password) {
        registry.putEntry(Constants.CONNECTION_PASSWORD, password);
    }
}
