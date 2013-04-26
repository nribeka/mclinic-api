package com.mclinic.api.dao;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.CredentialDaoImpl;
import com.mclinic.api.model.Credential;

/**
 * TODO: Write brief description about the class here.
 */
@ImplementedBy(CredentialDaoImpl.class)
public interface CredentialDao extends LocalDao<Credential> {
}
