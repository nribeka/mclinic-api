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
package com.mclinic.api.model.resolver;

import com.google.inject.Inject;
import com.mclinic.api.config.Configuration;
import com.mclinic.search.api.model.resolver.BaseResolver;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.net.URLConnection;

public abstract class BaseOpenmrsResolver extends BaseResolver {

    @Inject
    private Configuration configuration;

    /**
     * Get the default openmrs configuration for this resolver.
     *
     * @return the default openmrs configuration for this resolver.
     */
    protected Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Add authentication information to the url connection.
     *
     * @param connection the original connection without authentication information.
     * @return the url connection with authentication information.
     */
    @Override
    public URLConnection authenticate(final URLConnection connection) throws IOException {
        String authorization = getConfiguration().getUsername() + ":" + getConfiguration().getPassword();
        String encodedAuthorization = new String(Base64.encodeBase64(authorization.getBytes()));
        connection.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
        return connection;
    }
}
