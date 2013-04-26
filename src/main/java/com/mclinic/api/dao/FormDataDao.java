package com.mclinic.api.dao;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.FormDataDaoImpl;
import com.mclinic.api.model.FormData;

/**
 * TODO: Write brief description about the class here.
 */
@ImplementedBy(FormDataDaoImpl.class)
public interface FormDataDao extends LocalDao<FormData> {
}
