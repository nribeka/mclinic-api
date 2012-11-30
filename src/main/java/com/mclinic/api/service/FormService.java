package com.mclinic.api.service;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Form;
import com.mclinic.api.service.impl.FormServiceImpl;

import java.util.List;

/**
 * Service handling all operation to the @{Form} actor/model
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
@ImplementedBy (FormServiceImpl.class)
public interface FormService {
	
    public Form saveForm(Form form);

    public Form updateForm(Form form);
    
    public Form getFormById(Integer id);

    public List<Form> getAllForms();

    public void deleteForm(Form form);

    public void deleteAllForms();
}
