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

import java.net.URLConnection;

import com.mclinic.api.registry.ServerConfigRegistry;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.resolver.Resolver;
import com.mclinic.search.api.util.ResolverUtil;
import com.mclinic.util.Constants;

public abstract class AbstractResolver implements Resolver {

    private ServerConfigRegistry registry;

    public AbstractResolver() {
        registry = Context.getInstance(ServerConfigRegistry.class);
    }

    protected String getServer() {
        return registry.getEntryValue(Constants.SERVER);
    }

    @Override
    public URLConnection authenticate(final URLConnection connection) {
        String basicAuth =
                ResolverUtil.getBasicAuth(
                        registry.getEntryValue(Constants.CONNECTION_USERNAME),
                        registry.getEntryValue(Constants.CONNECTION_PASSWORD));
        connection.setRequestProperty(Constants.CONNECTION_AUTHORIZATION, basicAuth);
        return connection;
    }
}
