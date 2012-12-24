package com.mclinic.api.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.CohortDao;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.service.CohortService;

public class CohortServiceImpl implements CohortService {

    @Inject
    private CohortDao dao;

    @Override
    public Cohort createCohort(final Cohort cohort) {
        return dao.createCohort(cohort);
    }

    @Override
    public Cohort updateCohort(final Cohort cohort) {
        return dao.updateCohort(cohort);
    }

    @Override
    public Cohort getCohortByUuid(final String uuid) {
        return dao.getCohortByUuid(uuid);
    }

    @Override
    public List<Cohort> getCohortsByName(final String name) {
        return dao.getCohortsByName(name);
    }

    @Override
    public List<Cohort> getAllCohorts() {
        return dao.getAllCohorts();
    }

    @Override
    public void deleteCohort(final Cohort cohort) {
        dao.deleteCohort(cohort);
    }

    @Override
    public void deleteAllCohorts() {
        dao.deleteAllCohorts();
    }
}