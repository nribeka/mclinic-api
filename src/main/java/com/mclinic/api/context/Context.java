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

import com.google.inject.Injector;
import com.mclinic.api.config.Configuration;
import com.mclinic.api.service.CohortService;
import com.mclinic.api.service.FormService;
import com.mclinic.api.service.ObservationService;
import com.mclinic.api.service.PatientService;
import com.mclinic.api.service.UserService;
import com.mclinic.search.api.context.ServiceContext;
import com.mclinic.search.api.model.object.Searchable;
import com.mclinic.search.api.model.resolver.Resolver;
import com.mclinic.search.api.model.serialization.Algorithm;
import com.mclinic.search.api.resource.ResourceConstants;
import org.apache.lucene.queryParser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * TODO: Write brief description about the class here.
 */
public class Context {

    private Injector injector;

    private static final ThreadLocal<UserContext> userContextHolder = new ThreadLocal<UserContext>();

    public Context(final Injector injector) throws IOException {
        setInjector(injector);
        initService(injector);
        initConfiguration(injector);
    }

    private void setInjector(final Injector injector) {
        this.injector = injector;
    }

    private void initService(final Injector injector) throws IOException {
        // TODO: this should be replaced with a classpath scanner.
        // Approach:
        // * Create marker (annotation) for Object, Algorithm and Resolver.
        // * Register it to the ServiceContext of the search api.
        // Once we have classpath scanner we can probably kick off the scanner here.
        // TODO: a better approach will be reading the config and the loading the class
        // Approach:
        // * Get the configuration file.
        // * Read the object, algorithm, and resolver line to get the class name.
        // * Do: Class c = Class.forName() using the class name from above.
        // * Do: Object o = inject.getInstance(c).
        // * Register object according to their type registerObject, registerAlgorithm, or registerResolver.
        // TODO: Create a list of all available configuration file to load j2l automatically.
        // Approach:
        // * Create a list of all configuration file (list of j2l file names, just like they list hbm files).
        // * TODO: need to add a new method in the search api to register resource using InputStream --done
        // * Read each line and use Class.getResourceAsStream(path to j2l) and then register each of them.
        ServiceContext serviceContext = injector.getInstance(ServiceContext.class);

        InputStream configListStream = Context.class.getResourceAsStream("../service/j2l/config.list");
        BufferedReader reader = new BufferedReader(new InputStreamReader(configListStream));

        String line;
        InputStream inputStream;
        while ((line = reader.readLine()) != null) {
            try {
                inputStream = Context.class.getResourceAsStream("../service/j2l/" + line);
                Properties properties = new Properties();
                properties.load(inputStream);
                inputStream.close();

                String searchableName = properties.getProperty(ResourceConstants.RESOURCE_SEARCHABLE);
                Class searchableClass = Class.forName(searchableName);
                Searchable searchable = (Searchable) injector.getInstance(searchableClass);
                serviceContext.registerSearchable(searchable);

                String algorithmName = properties.getProperty(ResourceConstants.RESOURCE_ALGORITHM_CLASS);
                Class algorithmClass = Class.forName(algorithmName);
                Algorithm algorithm = (Algorithm) injector.getInstance(algorithmClass);
                serviceContext.registerAlgorithm(algorithm);

                String resolverName = properties.getProperty(ResourceConstants.RESOURCE_URI_RESOLVER_CLASS);
                Class resolverClass = Class.forName(resolverName);
                Resolver resolver = (Resolver) injector.getInstance(resolverClass);
                serviceContext.registerResolver(resolver);

                inputStream = Context.class.getResourceAsStream("../service/j2l/" + line);
                serviceContext.registerResource(inputStream);
                inputStream.close();
            } catch (ClassNotFoundException e) {
                throw new IOException("Unable to register resource for " + line, e);
            }
        }
        configListStream.close();
    }

    private void initConfiguration(final Injector injector) throws IOException {
        if (getUserContext() != null) {
            Configuration savedConfiguration = getUserContext().getConfiguration();
            Configuration configuration = injector.getInstance(Configuration.class);
            configuration.configure(
                    savedConfiguration.getUsername(), savedConfiguration.getPassword(), savedConfiguration.getServer());
        }
    }

    private UserContext getUserContext() {
        return userContextHolder.get();
    }

    private void setUserContext(final UserContext userContext) {
        userContextHolder.set(userContext);
    }

    private void removeUserContext() {
        userContextHolder.remove();
    }

    public void openSession() {
        setUserContext(new UserContext());
    }

    public void closeSession() {
        removeUserContext();
    }

    //TODO: Need to throw AuthorizationException when userContext is null
    public void authenticate(final String username, final String password, final String server)
            throws IOException, ParseException {
        getUserContext().authenticate(username, password, server, getUserService());

        Configuration configuration = getInjector().getInstance(Configuration.class);
        configuration.configure(username, password, server);
        getUserContext().setConfiguration(configuration);
    }

    public void deauthenticate() throws IOException {
        if (getUserContext() == null)
            throw new IOException("UserContext is not ready. You probably missed the openSession() call?");
        getUserContext().deauthenticate();
    }

    public boolean isAuthenticated() throws IOException {
        if (getUserContext() == null)
            throw new IOException("UserContext is not ready. You probably missed the openSession() call?");
        return getUserContext().isAuthenticated();
    }

    private Injector getInjector() throws IOException {
        if (injector == null)
            throw new IOException("Injector that will wired up the API is not ready.");
        return injector;
    }

    // TODO: need to bound the service class to prevent user from accessing the internal guice structure
    // Only open user to service layer. To do this:
    // * Make all *Service interface to extends Service
    // * Change this method signature to <T extends Service>
    public <T> T getService(final Class<T> serviceClass) throws IOException {
        return getInjector().getInstance(serviceClass);
    }

    // TODO: Need to throw exception when the injector is still null
    public CohortService getCohortService() throws IOException {
        return getService(CohortService.class);
    }

    public FormService getFormService() throws IOException {
        return getService(FormService.class);
    }

    public ObservationService getObservationService() throws IOException {
        return getService(ObservationService.class);
    }

    public PatientService getPatientService() throws IOException {
        return getService(PatientService.class);
    }

    public UserService getUserService() throws IOException {
        return getService(UserService.class);
    }
}
