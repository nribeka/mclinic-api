package com.mclinic.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.CohortDao;
import com.mclinic.api.model.Cohort;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.resource.Resource;
import com.mclinic.search.api.util.StringUtil;
import com.mclinic.util.Constants;

public class CohortDaoImpl implements CohortDao {

    @Inject
    private Logger log;

    @Inject
    private RestAssuredService service;

    private static final String TAG = CohortDao.class.getSimpleName();

    @Override
    public Cohort createCohort(final Cohort cohort) {

        Object object = null;
        try {
            Resource resource = Context.getResource(Constants.COHORT_RESOURCE);
            object = service.createObject(cohort, resource);
        } catch (Exception e) {
            log.error(TAG, "Error creating cohort.", e);
        }
        return (Cohort) object;
    }

    @Override
    public Cohort updateCohort(final Cohort cohort) {

        try {
            Resource resource = Context.getResource(Constants.COHORT_RESOURCE);
            service.updateObject(cohort, resource);
        } catch (Exception e) {
            log.error(TAG, "Error updating cohort.", e);
        }
        return null;
    }

    @Override
    public Cohort getCohortByUuid(final String uuid) {

        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(uuid))
            searchQuery = "uuid: " + StringUtil.quote(uuid);

        Cohort cohort = null;
        try {
            cohort = service.getObject(searchQuery, Cohort.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting cohort using query: " + searchQuery, e);
        }
        return cohort;
    }

    @Override
    public List<Cohort> getCohortsByName(final String name) {

        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(name))
            searchQuery = "name: " + StringUtil.quote(name + "*");

        List<Cohort> cohorts = new ArrayList<Cohort>();
        try {
            cohorts = service.getObjects(searchQuery, Cohort.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting cohorts using query: " + searchQuery, e);
        }
        return cohorts;
    }

    @Override
    public List<Cohort> getAllCohorts() {

        List<Cohort> cohorts = new ArrayList<Cohort>();
        try {
            cohorts = service.getObjects(StringUtil.EMPTY, Cohort.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting all cohorts.", e);
        }
        return cohorts;
    }

    @Override
    public void deleteCohort(final Cohort cohort) {

        try {
            Resource resource = Context.getResource(Constants.COHORT_RESOURCE);
            service.invalidate(cohort, resource);
        } catch (Exception e) {
            log.error(TAG, "Error deleting cohort.", e);
        }
    }

    @Override
    public void deleteAllCohorts() {
        // TODO Auto-generated method stub
    }
}