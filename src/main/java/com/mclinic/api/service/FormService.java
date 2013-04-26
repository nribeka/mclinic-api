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
package com.mclinic.api.service;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Form;
import com.mclinic.api.model.FormData;
import com.mclinic.api.model.FormTemplate;
import com.mclinic.api.service.impl.FormServiceImpl;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Service handling all operation to the @{Form} actor/model
 */
@ImplementedBy(FormServiceImpl.class)
public interface FormService {

    /**
     * Download a single form record from the form rest resource into the local lucene repository.
     *
     * @param uuid the uuid of the form.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download form with matching uuid.
     */
    void downloadFormByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Download all forms with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the form to be downloaded. When empty, will return all forms available.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download all form with partially matched name.
     * @should download all form when name is empty.
     */
    void downloadFormsByName(final String name) throws IOException, ParseException;

    /**
     * @param uuid the form uuid.
     * @return form with matching uuid or null when no form match the uuid.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return form with matching uuid.
     * @should return null when no form match the uuid.
     */
    Form getFormByUuid(final String uuid) throws IOException, ParseException;

    /**
     * @return all registered forms or empty list when no form is registered.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return all registered forms.
     * @should return empty list when no form is registered.
     */
    List<Form> getAllForms() throws IOException, ParseException;

    /**
     * Delete form from the repository.
     *
     * @param form the form to be deleted.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void deleteForm(final Form form) throws IOException, ParseException;

    /**
     * Save a new form template to the repository.
     *
     * @param formTemplate the form template to be saved.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void saveFormTemplate(final FormTemplate formTemplate) throws IOException, ParseException;

    /**
     * Get a form template by the uuid.
     *
     * @param uuid the form template uuid.
     * @return the form template.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    FormTemplate getFormTemplateByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Get all saved form templates from the local repository.
     *
     * @return all saved form templates or empty list when there's no form template saved.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    List<FormTemplate> getAllFormTemplates() throws IOException, ParseException;

    /**
     * Delete a form template from the repository.
     *
     * @param formTemplate the form template to be deleted.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void deleteFormTemplate(final FormTemplate formTemplate) throws IOException, ParseException;

    /**
     * Save a new form data object to the database.
     *
     * @param formData the form data to be saved.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void saveFormData(final FormData formData) throws IOException, ParseException;

    /**
     * @param uuid
     * @return
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    FormData getFormDataByUuid(final String uuid) throws IOException, ParseException;

    /**
     * @param status
     * @return
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    List<FormData> getAllFormData(final String status) throws IOException, ParseException;

    /**
     * @param userUuid
     * @param status
     * @return
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    List<FormData> getFormDataByUser(final String userUuid, final String status) throws IOException, ParseException;

    /**
     * @param patientUuid
     * @param status
     * @return
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    List<FormData> getFormDataByPatient(final String patientUuid, final String status) throws IOException, ParseException;

    /**
     * @param formData
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void deleteFormDate(final FormData formData) throws IOException, ParseException;
}
