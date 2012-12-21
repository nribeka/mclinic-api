package com.mclinic.api.service;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Form;
import com.mclinic.api.service.impl.FormServiceImpl;

/**
 * Service handling all operation to the @{Form} actor/model
 */
@ImplementedBy(FormServiceImpl.class)
public interface FormService {

    public Form saveForm(final Form form);

    public Form updateForm(final Form form);

    public Form getFormById(final Integer id);

    public List<Form> getAllForms();

    public void deleteForm(final Form form);

    public void deleteAllForms();
}
