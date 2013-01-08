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

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.FormDao;
import com.mclinic.api.dao.PatientDao;
import com.mclinic.api.model.Form;
import com.mclinic.api.model.Patient;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.resource.Resource;
import com.mclinic.search.api.util.StringUtil;
import com.mclinic.util.Constants;

public class FormDaoImpl implements FormDao {

    @Inject
    private Logger log;

    @Inject
    private RestAssuredService service;

    private static final String TAG = PatientDao.class.getSimpleName();

    @Override
    public Form saveForm(final Form form) {
        Object object = null;
        try {
            Resource resource = Context.getResource(Constants.FORM_RESOURCE);
            object = service.createObject(form, resource);
        } catch (Exception e) {
            log.error(TAG, "Error creating form.", e);
        }
        return (Form) object;
    }

    @Override
    public Form updateForm(final Form form) {
        Object object = null;
        try {
            Resource resource = Context.getResource(Constants.FORM_RESOURCE);
            object = service.updateObject(form, resource);
        } catch (Exception e) {
            log.error(TAG, "Error updating form.", e);
        }
        return (Form) object;
    }

    @Override
    public Form getFormByUuid(final String uuid) {
        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(uuid))
            searchQuery = "uuid: " + StringUtil.quote(uuid);

        Form form = null;
        try {
            form = service.getObject(searchQuery, Form.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting form using query: " + searchQuery, e);
        }
        return form;
    }

    @Override
    public List<Form> getAllForms() {
        List<Form> forms = new ArrayList<Form>();
        try {
            forms = service.getObjects(StringUtil.EMPTY, Form.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting all forms.", e);
        }
        return forms;
    }

    @Override
    public void deleteForm(final Form form) {
        try {
            Resource resource = Context.getResource(Constants.FORM_RESOURCE);
            service.invalidate(form, resource);
        } catch (Exception e) {
            log.error(TAG, "Error deleting patient.", e);
        }
    }

    @Override
    public void deleteAllForms() {
        // TODO Auto-generated method stub

    }

}
