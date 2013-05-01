package com.mclinic.api.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.search.api.module.SearchModule;

/**
 * TODO: Write brief description about the class here.
 */
class ServiceContext {

    private Injector injector;

    private static ServiceContext ourInstance;

    public static ServiceContext getInstance() {
        if (ourInstance == null)
            ourInstance = new ServiceContext();
        return ourInstance;
    }

    private ServiceContext() {
    }

    public void start() {
        // TODO: pull this and make module as part of the parameter
        String tmpDirectory = System.getProperty("java.io.tmpdir");
        injector = Guice.createInjector(new SearchModule(), new MuzimaModule(tmpDirectory, "uuid"));
    }

    public <T> T getService(Class<T> serviceClass) {
        return injector.getInstance(serviceClass);
    }
}
