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

/**
 * TODO: Write brief description about the class here.
 */
public class DefaultResource {

    /*
     * TODO: Need to use external configuration. This is for testing purpose only.
     */
    private static final String[] configurations = new String[]{
            "# Resource\n" +
                    "resource.name=Local Credential Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.Credential\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.CredentialAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.LocalResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "username=$['username']\n" +
                    "userUuid=$['user.uuid']",
            "# Resource\n" +
                    "resource.name=Local Form Data Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.FormData\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.FormDataAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.LocalResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "status=$['status']\n" +
                    "userUuid=$['user.uuid']\n" +
                    "patientUuid=$['patient.uuid']\n" +
                    "templateUuid=$['template.uuid']",
            "# Resource\n" +
                    "resource.name=Local Form Template Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.FormTemplate\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.FormTemplateAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.LocalResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "formUuid=$['form.uuid']",
            "# Resource\n" +
                    "resource.name=Cohort Member Resource\n" +
                    "node.root=$['results']\n" +
                    "resource.object=com.mclinic.api.model.Member\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.MemberAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.MemberCohortResolver\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "cohortUuid=$['cohort.uuid']\n" +
                    "patientUuid=$['patient.uuid']\n",
            "# Resource configuration\n" +
                    "resource.name=Search Cohort Resource\n" +
                    "node.root=$['results']\n" +
                    "resource.object=com.mclinic.api.model.Cohort\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.CohortAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.SearchCohortResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "name=$['name']",
            "# Resource configuration\n" +
                    "resource.name=Search Form Resource\n" +
                    "node.root=$['results']\n" +
                    "resource.object=com.mclinic.api.model.Form\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.FormAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.SearchFormResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "name=$['name']",
            "# Resource configuration\n" +
                    "resource.name=Search Observation Resource\n" +
                    "node.root=$['results']\n" +
                    "resource.object=com.mclinic.api.model.Observation\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.ObservationAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.SearchObservationResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "patientUuid=$['person.uuid']\n" +
                    "conceptUuid=$['concept.uuid']\n" +
                    "conceptName=$['concept.name.name']",
            "# Resource configuration\n" +
                    "resource.name=Search Patient Resource\n" +
                    "node.root=$['results']\n" +
                    "resource.object=com.mclinic.api.model.Patient\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.PatientAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.SearchPatientResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "givenName=$['personName.givenName']\n" +
                    "middleName=$['personName.middleName']\n" +
                    "familyName=$['personName.familyName']\n" +
                    "identifier=$['patientIdentifier.identifier']",
            "# Resource configuration\n" +
                    "resource.name=Search Privilege Resource\n" +
                    "node.root=$['results']\n" +
                    "resource.object=com.mclinic.api.model.Privilege\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.PrivilegeAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.SearchPrivilegeResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "name=$['name']",
            "# Resource configuration\n" +
                    "resource.name=Search Role Resource\n" +
                    "node.root=$['results']\n" +
                    "resource.object=com.mclinic.api.model.Role\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.RoleAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.SearchRoleResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "name=$['name']",
            "# Resource configuration\n" +
                    "resource.name=Search User Resource\n" +
                    "node.root=$['results']\n" +
                    "resource.object=com.mclinic.api.model.User\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.UserAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.SearchUserResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "givenName=$['person.personName.givenName']\n" +
                    "middleName=$['person.personName.middleName']\n" +
                    "familyName=$['person.personName.familyName']\n" +
                    "username=$['username']\n" +
                    "systemId=$['systemId']",
            "# Resource configuration\n" +
                    "resource.name=Uuid Cohort Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.Cohort\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.CohortAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.UuidCohortResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "name=$['name']",
            "# Resource configuration\n" +
                    "resource.name=Uuid Form Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.Form\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.FormAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.UuidFormResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "name=$['name']",
            "# Resource configuration\n" +
                    "resource.name=Uuid Observation Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.Observation\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.ObservationAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.UuidObservationResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "patientUuid=$['person.uuid']\n" +
                    "conceptUuid=$['concept.uuid']\n" +
                    "conceptName=$['concept.name.name']",
            "# Resource configuration\n" +
                    "resource.name=Uuid Patient Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.Patient\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.PatientAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.UuidPatientResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "givenName=$['personName.givenName']\n" +
                    "middleName=$['personName.middleName']\n" +
                    "familyName=$['personName.familyName']\n" +
                    "identifier=$['patientIdentifier.identifier']",
            "# Resource configuration\n" +
                    "resource.name=Uuid Privilege Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.Privilege\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.PrivilegeAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.UuidPrivilegeResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "name=$['name']",
            "# Resource configuration\n" +
                    "resource.name=Uuid Role Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.Role\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.RoleAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.UuidRoleResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "name=$['name']\n",
            "# Resource configuration\n" +
                    "resource.name=Uuid User Resource\n" +
                    "node.root=$\n" +
                    "resource.object=com.mclinic.api.model.User\n" +
                    "algorithm.class=com.mclinic.api.model.algorithm.UserAlgorithm\n" +
                    "resolver.class=com.mclinic.api.model.resolver.UuidUserResolver\n" +
                    "field.unique=uuid\n" +
                    "\n" +
                    "# Searchable Fields\n" +
                    "uuid=$['uuid']\n" +
                    "givenName=$['person.personName.givenName']\n" +
                    "middleName=$['person.personName.middleName']\n" +
                    "familyName=$['person.personName.familyName']\n" +
                    "username=$['username']\n" +
                    "systemId=$['systemId']\n"
    };

    public static String[] getDefaultConfiguration() {
        return configurations;
    }
}
