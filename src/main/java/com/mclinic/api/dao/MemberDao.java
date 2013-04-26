package com.mclinic.api.dao;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.MemberDaoImpl;
import com.mclinic.api.model.Member;

/**
 * TODO: Write brief description about the class here.
 */
@ImplementedBy(MemberDaoImpl.class)
public interface MemberDao extends LocalDao<Member> {
}
