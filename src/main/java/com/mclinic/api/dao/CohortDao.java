package com.mclinic.api.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.CohortDaoImpl;
import com.mclinic.api.model.Cohort;

@ImplementedBy(CohortDaoImpl.class)
public interface CohortDao {

    Cohort createCohort(final Cohort cohort);

    Cohort updateCohort(final Cohort cohort);

    Cohort getCohortByUuid(final String uuid);

    List<Cohort> getAllCohorts();

    List<Cohort> getCohortsByName(final String name);

    void deleteAllCohorts();

    void deleteCohort(final Cohort cohort);

}
