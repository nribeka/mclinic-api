package com.mclinic.api.dao.impl;

import java.util.List;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.RestAssuredService;
import com.burkeware.search.api.logger.Logger;
import com.burkeware.search.api.util.StringUtil;
import com.google.inject.Inject;
import com.mclinic.api.dao.ObservationDao;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.util.Constants;

public class ObservationDaoImpl implements ObservationDao {

    @Inject
    private RestAssuredService service;

    @Inject
    private Logger log;

    private String TAG = "ObservationDao";

    @Override
    public Observation createObservation(Observation observation) {
        try {
            service.createObject(observation, Context.getResource(Constants.OBSERVATION));
        } catch (Exception e) {
            log.debug(TAG, "Error creating observation " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Observation updateObservation(Observation observation) {
        try {
            service.updateObject(observation, Context.getResource(Constants.OBSERVATION));
        } catch (Exception e) {
            log.debug(TAG, "Error updating observation " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Observation getObservationByUUID(String uuid) {
        try {
            return service.getObject("uuid: " + StringUtil.quote(uuid), Observation.class);
        } catch (Exception e) {
            log.debug(TAG, "Error getting observation by uuid " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<Observation> getAllObservations(Patient patient) {
        try {
            return service.getObjects("patientUuid: " + StringUtil.quote(patient.getUuid()), Observation.class);
        } catch (Exception e) {
            log.debug(TAG, "Error fetching all obs " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void deleteObservation(Observation observation) {
        try {
            service.invalidate(observation, Context.getResource(Constants.OBSERVATION));
        } catch (Exception e) {
            log.debug(TAG, "Error delete observation " + e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteAllObservations(Patient patient) {
        // TODO Auto-generated method stub
    }
}