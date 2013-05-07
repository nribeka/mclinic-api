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
import com.mclinic.api.model.Cohort;
import com.mclinic.api.model.Credential;
import com.mclinic.api.model.Form;
import com.mclinic.api.model.FormData;
import com.mclinic.api.model.FormTemplate;
import com.mclinic.api.model.Member;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.api.model.Privilege;
import com.mclinic.api.model.Role;
import com.mclinic.api.model.User;
import com.mclinic.api.model.algorithm.CohortAlgorithm;
import com.mclinic.api.model.algorithm.CredentialAlgorithm;
import com.mclinic.api.model.algorithm.FormAlgorithm;
import com.mclinic.api.model.algorithm.FormDataAlgorithm;
import com.mclinic.api.model.algorithm.FormTemplateAlgorithm;
import com.mclinic.api.model.algorithm.MemberAlgorithm;
import com.mclinic.api.model.algorithm.ObservationAlgorithm;
import com.mclinic.api.model.algorithm.PatientAlgorithm;
import com.mclinic.api.model.algorithm.PrivilegeAlgorithm;
import com.mclinic.api.model.algorithm.RoleAlgorithm;
import com.mclinic.api.model.algorithm.UserAlgorithm;
import com.mclinic.api.model.resolver.LocalResolver;
import com.mclinic.api.model.resolver.MemberCohortResolver;
import com.mclinic.api.model.resolver.SearchCohortResolver;
import com.mclinic.api.model.resolver.SearchFormResolver;
import com.mclinic.api.model.resolver.SearchObservationResolver;
import com.mclinic.api.model.resolver.SearchPatientResolver;
import com.mclinic.api.model.resolver.SearchPrivilegeResolver;
import com.mclinic.api.model.resolver.SearchRoleResolver;
import com.mclinic.api.model.resolver.SearchUserResolver;
import com.mclinic.api.model.resolver.UuidCohortResolver;
import com.mclinic.api.model.resolver.UuidFormResolver;
import com.mclinic.api.model.resolver.UuidObservationResolver;
import com.mclinic.api.model.resolver.UuidPatientResolver;
import com.mclinic.api.model.resolver.UuidPrivilegeResolver;
import com.mclinic.api.model.resolver.UuidRoleResolver;
import com.mclinic.api.model.resolver.UuidUserResolver;
import com.mclinic.api.service.CohortService;
import com.mclinic.api.service.FormService;
import com.mclinic.api.service.ObservationService;
import com.mclinic.api.service.PatientService;
import com.mclinic.api.service.UserService;
import com.mclinic.search.api.context.ServiceContext;
import com.mclinic.search.api.model.object.BaseSearchable;
import com.mclinic.search.api.model.resolver.BaseResolver;
import com.mclinic.search.api.model.serialization.BaseAlgorithm;
import org.apache.lucene.queryParser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * TODO: Write brief description about the class here.
 */
public class Context {

    private Injector injector;

    private static final ThreadLocal<UserContext> userContextHolder = new ThreadLocal<UserContext>();

    private static final List<Class<? extends BaseSearchable>> searachables = Arrays.asList(
            Cohort.class, Credential.class, Form.class, FormData.class, FormTemplate.class, Member.class,
            Observation.class, Patient.class, Privilege.class, Role.class, User.class
    );

    private static final List<Class<? extends BaseAlgorithm>> algorithms = Arrays.asList(
            CohortAlgorithm.class, CredentialAlgorithm.class, FormAlgorithm.class, FormDataAlgorithm.class,
            FormTemplateAlgorithm.class, MemberAlgorithm.class, ObservationAlgorithm.class, PatientAlgorithm.class,
            PrivilegeAlgorithm.class, RoleAlgorithm.class, UserAlgorithm.class
    );

    private static final List<Class<? extends BaseResolver>> resolvers = Arrays.asList(
            LocalResolver.class, MemberCohortResolver.class, SearchCohortResolver.class, SearchFormResolver.class,
            SearchObservationResolver.class, SearchPatientResolver.class, SearchPrivilegeResolver.class,
            SearchRoleResolver.class, SearchUserResolver.class, UuidCohortResolver.class, UuidFormResolver.class,
            UuidObservationResolver.class, UuidPatientResolver.class, UuidPrivilegeResolver.class,
            UuidRoleResolver.class, UuidUserResolver.class
    );

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
        ServiceContext serviceContext = injector.getInstance(ServiceContext.class);
        for (Class<? extends BaseSearchable> searachable : searachables)
            serviceContext.registerObject(injector.getInstance(searachable));
        for (Class<? extends BaseAlgorithm> algorithm : algorithms)
            serviceContext.registerAlgorithm(injector.getInstance(algorithm));
        for (Class<? extends BaseResolver> resolver : resolvers)
            serviceContext.registerResolver(injector.getInstance(resolver));
        // TODO: Create a list of all available configuration file to load j2l automatically.
        // Approach:
        // * Create a list of all configuration file (list of j2l file names, just like they list hbm files).
        // * TODO: need to add a new method in the search api to register resource using InputStream --done
        // * Read each line and use Class.getResourceAsStream(path to j2l) and then register each of them.
        URL configUrl = ContextFactory.class.getResource("../service/j2l");
        serviceContext.registerResources(new File(configUrl.getPath()));
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
