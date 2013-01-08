/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package com.mclinic.api.service;

import java.io.File;
import java.net.URL;
import java.util.List;

import com.mclinic.api.model.Form;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.search.api.Context;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FormServiceTest {

    private FormService formService;

    private AdministrativeService service;

    @Before
    public void prepare() throws Exception {
        URL repositoryPath = AdministrativeServiceTest.class.getResource("../j2l");
        URL lucenePath = AdministrativeServiceTest.class.getResource("../lucene");
        Context.initialize(new MuzimaModule(lucenePath.getPath(), "uuid"));

        service = Context.getInstance(AdministrativeService.class);
        Assert.assertNotNull(service);

        service.initializeRepository(repositoryPath.getPath());

        URL jsonPath = AdministrativeServiceTest.class.getResource("../json/form");
        service.loadForms(new File(jsonPath.getPath()));

        formService = Context.getInstance(FormService.class);
        Assert.assertNotNull(formService);
    }

    @After
    public void cleanUp() {
        URL lucenePath = AdministrativeServiceTest.class.getResource("../lucene");

        File luceneDirectory = new File(lucenePath.getPath());
        for (String filename : luceneDirectory.list()) {
            File file = new File(luceneDirectory, filename);
            Assert.assertTrue(file.delete());
        }
    }

    /**
     * @verifies return form with matching uuid
     * @see FormService#getFormByUuid(String)
     */
    @Test
    public void getFormByUuid_shouldReturnFormWithMatchingUuid() throws Exception {
        String uuid = "c05295a0-1691-11df-97a5-7038c432aabf";
        Form form = formService.getFormByUuid(uuid);
        Assert.assertNotNull(form);
    }

    /**
     * @verifies return null when no form match the uuid
     * @see FormService#getFormByUuid(String)
     */
    @Test
    public void getFormByUuid_shouldReturnNullWhenNoFormMatchTheUuid() throws Exception {
        String randomUuid = "1234";
        Form form = formService.getFormByUuid(randomUuid);
        Assert.assertNull(form);
    }

    /**
     * @verifies return all registered forms
     * @see FormService#getAllForms()
     */
    @Test
    public void getAllForms_shouldReturnAllRegisteredForms() throws Exception {
        List<Form> forms = formService.getAllForms();
        Assert.assertNotNull(forms);
        Assert.assertFalse(forms.isEmpty());
    }

    /**
     * @verifies return empty list when no form is registered
     * @see FormService#getAllForms()
     */
    @Test
    public void getAllForms_shouldReturnEmptyListWhenNoFormIsRegistered() throws Exception {
        List<Form> forms = formService.getAllForms();
        Assert.assertNotNull(forms);
        Assert.assertFalse(forms.isEmpty());

        for (Form form : forms)
            formService.deleteForm(form);

        forms = formService.getAllForms();
        Assert.assertNotNull(forms);
        Assert.assertTrue(forms.isEmpty());
    }
}
