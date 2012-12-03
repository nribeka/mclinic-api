package com.mclinic.api.model.resolver;

import java.net.URLConnection;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.resolver.Resolver;
import com.burkeware.search.api.util.ResolverUtil;
import com.mclinic.api.registry.ServerConfigRegistry;
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