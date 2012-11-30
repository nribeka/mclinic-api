package com.mclinic.api.service;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.service.impl.CohortServiceImpl;

import java.util.List;

/**
 * Service handling all operation to the @{Cohort} actor/model
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
@ImplementedBy (CohortServiceImpl.class)
public interface CohortService {
	
    Cohort createCohort(Cohort cohort);

    Cohort updateCohort(Cohort cohort);
    
    Cohort getCohortByUUID(String uuid);
    
    List<Cohort> getCohortsByName(String name);

    List<Cohort> getAllCohorts();

    void deleteCohort(Cohort cohort);

    void deleteAllCohorts();
}
