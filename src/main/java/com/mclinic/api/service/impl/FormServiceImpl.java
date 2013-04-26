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
package com.mclinic.api.service.impl;

import com.google.inject.Inject;
import com.mclinic.api.dao.FormDao;
import com.mclinic.api.dao.FormDataDao;
import com.mclinic.api.dao.FormTemplateDao;
import com.mclinic.api.model.Form;
import com.mclinic.api.model.FormData;
import com.mclinic.api.model.FormTemplate;
import com.mclinic.api.service.FormService;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

public class FormServiceImpl implements FormService {

    @Inject
    private FormDao formDao;

    @Inject
    private FormDataDao formDataDao;

    @Inject
    private FormTemplateDao formTemplateDao;

    /**
     * Download a single form record from the form rest resource into the local lucene repository.
     *
     * @param uuid the uuid of the form.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download form with matching uuid.
     */
    @Override
    public void downloadFormByUuid(final String uuid) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Download all forms with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the form to be downloaded. When empty, will return all forms available.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download all form with partially matched name.
     * @should download all form when name is empty.
     */
    @Override
    public void downloadFormsByName(final String name) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @param uuid the form uuid.
     * @return form with matching uuid or null when no form match the uuid.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return form with matching uuid.
     * @should return null when no form match the uuid.
     */
    @Override
    public Form getFormByUuid(final String uuid) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @return all registered forms or empty list when no form is registered.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return all registered forms.
     * @should return empty list when no form is registered.
     */
    @Override
    public List<Form> getAllForms() throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Delete form from the repository.
     *
     * @param form the form to be deleted.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     */
    @Override
    public void deleteForm(final Form form) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void saveFormTemplate(final FormTemplate formTemplate) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FormTemplate getFormTemplateByUuid(final String uuid) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FormTemplate> getAllFormTemplates() throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteFormTemplate(final FormTemplate formTemplate) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void saveFormData(final FormData formData) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FormData getFormDataByUuid(final String uuid) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FormData> getAllFormData(final String status) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FormData> getFormDataByUser(final String userUuid, final String status) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FormData> getFormDataByPatient(final String patientUuid, final String status) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteFormDate(final FormData formData) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
