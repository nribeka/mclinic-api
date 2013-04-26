package com.mclinic.api.dao;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.FormTemplateDaoImpl;
import com.mclinic.api.model.FormTemplate;

/**
 * TODO: Write brief description about the class here.
 */
@ImplementedBy(FormTemplateDaoImpl.class)
public interface FormTemplateDao extends LocalDao<FormTemplate> {
}
