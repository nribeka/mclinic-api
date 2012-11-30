package com.mclinic.api.dao;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.CohortDaoImpl;
import com.mclinic.api.model.Cohort;

import java.util.List;

@ImplementedBy (CohortDaoImpl.class)
public interface CohortDao {
	
    Cohort createCohort(Cohort cohort);

    Cohort updateCohort(Cohort cohort);
    
    Cohort getCohortByUUID(String uuid);

    List<Cohort> getCohortsByName(String name);
    
    List<Cohort> getAllCohorts();

    void deleteCohort(Cohort cohort);

    void deleteAllCohorts();

}
