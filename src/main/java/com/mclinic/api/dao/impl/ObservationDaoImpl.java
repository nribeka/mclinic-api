package com.mclinic.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.ObservationDao;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.resource.Resource;
import com.mclinic.search.api.util.StringUtil;
import com.mclinic.util.Constants;

public class ObservationDaoImpl implements ObservationDao {

    @Inject
    private Logger log;

    @Inject
    private RestAssuredService service;

    private static final String TAG = ObservationDao.class.getSimpleName();

    @Override
    public Observation createObservation(final Observation observation) {

        try {
            Resource resource = Context.getResource(Constants.OBSERVATION_RESOURCE);
            service.createObject(observation, resource);
        } catch (Exception e) {
            log.error(TAG, "Error creating observation.", e);
        }
        return null;
    }

    @Override
    public Observation updateObservation(final Observation observation) {

        try {
            Resource resource = Context.getResource(Constants.OBSERVATION_RESOURCE);
            service.updateObject(observation, resource);
        } catch (Exception e) {
            log.error(TAG, "Error updating observation.", e);
        }
        return null;
    }

    @Override
    public Observation getObservationByUuid(final String uuid) {

        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(uuid))
            searchQuery = "uuid: " + StringUtil.quote(uuid);

        Observation observation = null;
        try {
            observation = service.getObject(searchQuery, Observation.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting observation using query: " + searchQuery, e);
        }
        return observation;
    }

    @Override
    public List<Observation> getAllObservations(final Patient patient) {

        String searchQuery = StringUtil.EMPTY;
        if (patient != null && !StringUtil.isEmpty(patient.getUuid()))
            searchQuery = "patient: " + StringUtil.quote(patient.getUuid());

        List<Observation> observations = new ArrayList<Observation>();
        try {
            observations = service.getObjects(searchQuery, Observation.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting observations using query: " + searchQuery, e);
        }
        return observations;
    }

    @Override
    public void deleteObservation(final Observation observation) {

        try {
            Resource resource = Context.getResource(Constants.OBSERVATION_RESOURCE);
            service.invalidate(observation, resource);
        } catch (Exception e) {
            log.error(TAG, "Error deleting observation.", e);
        }
    }

    @Override
    public void deleteAllObservations(final Patient patient) {
        // TODO Auto-generated method stub
    }
}