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

import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.FormDao;
import com.mclinic.api.dao.PatientDao;
import com.mclinic.api.model.Form;
import com.mclinic.api.service.FormService;

public class FormServiceImpl implements FormService {

    @Inject
    private FormDao dao;

    @Override
    public Form saveForm(final Form form) {
        return dao.saveForm(form);
    }

    @Override
    public Form updateForm(final Form form) {
        return dao.updateForm(form);
    }

    @Override
    public Form getFormByUuid(final String uuid) {
        return dao.getFormByUuid(uuid);
    }

    @Override
    public List<Form> getAllForms() {
        return dao.getAllForms();
    }

    @Override
    public void deleteForm(final Form form) {
        dao.deleteForm(form);

    }

    @Override
    public void deleteAllForms() {
        // TODO Auto-generated method stub

    }

}
