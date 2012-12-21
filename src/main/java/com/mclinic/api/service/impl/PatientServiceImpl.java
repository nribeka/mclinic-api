package com.mclinic.api.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.PatientDao;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.PatientService;

public class PatientServiceImpl implements PatientService {

    @Inject
    private PatientDao dao;

    @Override
    public Patient createPatient(final Patient patient) {
        return dao.createPatient(patient);
    }

    @Override
    public Patient updatePatient(final Patient patient) {
        return dao.updatePatient(patient);
    }

    @Override
    public Patient getPatientByIdentifier(final String identifier) {
        return dao.getPatientByIdentifier(identifier);
    }

    @Override
    public List<Patient> getAllPatients() {
        return dao.getAllPatients();
    }

    @Override
    public void deletePatient(final Patient patient) {
        dao.deletePatient(patient);
    }

    @Override
    public void deleteAllPatients() {
        dao.deleteAllPatients();
    }

    @Override
    public Patient getPatientByUuid(final String uuid) {
        return dao.getPatientByUuid(uuid);
    }

    @Override
    public List<Patient> getPatientsByName(final String name) {
        return dao.getPatientsByName(name);
    }
}