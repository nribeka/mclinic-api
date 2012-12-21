package com.mclinic.api.service;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.service.impl.CohortServiceImpl;

/**
 * Service handling all operation to the @{Cohort} actor/model
 */
@ImplementedBy(CohortServiceImpl.class)
public interface CohortService {

    Cohort createCohort(final Cohort cohort);

    Cohort updateCohort(final Cohort cohort);

    Cohort getCohortByUUID(final String uuid);

    List<Cohort> getCohortsByName(final String name);

    List<Cohort> getAllCohorts();

    void deleteCohort(final Cohort cohort);

    void deleteAllCohorts();
}
