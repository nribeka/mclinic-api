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
package com.mclinic.api.dao.impl;

import com.google.inject.Inject;
import com.mclinic.api.dao.AdministrativeDao;
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
import com.mclinic.search.api.context.ServiceContext;
import com.mclinic.search.api.logger.Logger;

import java.io.File;
import java.io.IOException;

public class AdministrativeDaoImpl implements AdministrativeDao {

    @Inject
    private Logger log;

    @Inject
    private ServiceContext context;

    private static final String TAG = AdministrativeDaoImpl.class.getSimpleName();

    /**
     * Initialize the lucene repository with the resource configurations in the path.
     *
     * @param resourcePath the path where the resource configurations are stored.
     */
    @Override
    public void initializeRepository(final String resourcePath) throws IOException {
        initializeRepository(new File(resourcePath));
    }

    /**
     * Initialize the lucene repository with the resource configurations in the path.
     *
     * @param resourceDir the path where the resource configurations are stored.
     */
    @Override
    public void initializeRepository(final File resourceDir) throws IOException {
        context.registerObject(new Cohort());
        context.registerObject(new Member());
        context.registerObject(new Credential());
        context.registerObject(new Form());
        context.registerObject(new FormData());
        context.registerObject(new FormTemplate());
        context.registerObject(new Observation());
        context.registerObject(new Patient());
        context.registerObject(new Privilege());
        context.registerObject(new Role());
        context.registerObject(new User());

        context.registerAlgorithm(new CohortAlgorithm());
        context.registerAlgorithm(new MemberAlgorithm());
        context.registerAlgorithm(new CredentialAlgorithm());
        context.registerAlgorithm(new FormAlgorithm());
        context.registerAlgorithm(new FormDataAlgorithm());
        context.registerAlgorithm(new FormTemplateAlgorithm());
        context.registerAlgorithm(new ObservationAlgorithm());
        context.registerAlgorithm(new PatientAlgorithm());
        context.registerAlgorithm(new PrivilegeAlgorithm());
        context.registerAlgorithm(new RoleAlgorithm());
        context.registerAlgorithm(new UserAlgorithm());

        context.registerResolver(new MemberCohortResolver());
        context.registerResolver(new LocalResolver());
        context.registerResolver(new SearchCohortResolver());
        context.registerResolver(new SearchFormResolver());
        context.registerResolver(new SearchObservationResolver());
        context.registerResolver(new SearchPatientResolver());
        context.registerResolver(new SearchPrivilegeResolver());
        context.registerResolver(new SearchRoleResolver());
        context.registerResolver(new SearchUserResolver());
        context.registerResolver(new UuidCohortResolver());
        context.registerResolver(new UuidFormResolver());
        context.registerResolver(new UuidObservationResolver());
        context.registerResolver(new UuidPatientResolver());
        context.registerResolver(new UuidPrivilegeResolver());
        context.registerResolver(new UuidRoleResolver());
        context.registerResolver(new UuidUserResolver());

        context.registerResources(resourceDir);
    }
}
