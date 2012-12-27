package com.mclinic.api.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.ObservationDao;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.ObservationService;

public class ObservationServiceImpl implements ObservationService {

    @Inject
    private ObservationDao dao;

    @Override
    public Observation createObservation(final Observation observation) {
        return dao.createObservation(observation);
    }

    @Override
    public Observation updateObservation(final Observation observation) {
        return dao.updateObservation(observation);
    }

    @Override
    public Observation getObservationByUuid(final String uuid) {
        return dao.getObservationByUuid(uuid);
    }

    @Override
    public List<Observation> getAllObservations(final Patient patient) {
        return dao.getAllObservations(patient);
    }

    @Override
    public void deleteObservation(final Observation observation) {
        dao.deleteObservation(observation);
    }

    @Override
    public void deleteAllObservations(final Patient patient) {
        dao.deleteAllObservations(patient);
    }
}