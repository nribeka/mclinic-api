package com.mclinic.api.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.FormDaoImpl;
import com.mclinic.api.model.Form;

@ImplementedBy(FormDaoImpl.class)
public interface FormDao {

    Form saveForm(Form form);

    Form updateForm(Form form);

    Form getFormById(Integer id);

    List<Form> getAllForms();

    void deleteForm(Form form);

    void deleteAllForms();
}
