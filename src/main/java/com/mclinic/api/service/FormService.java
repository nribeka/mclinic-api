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

    Form saveForm(final Form form);

    Form updateForm(final Form form);

    Form getFormById(final Integer id);

    List<Form> getAllForms();

    void deleteForm(final Form form);

    void deleteAllForms();
}
