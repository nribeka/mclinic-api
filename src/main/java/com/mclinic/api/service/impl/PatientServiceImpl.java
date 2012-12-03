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
    public Patient createPatient(Patient patient) {
        return dao.createPatient(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return dao.updatePatient(patient);
    }

    @Override
    public Patient getPatientByIdentifier(String identifier) {
        return dao.getPatientByIdentifier(identifier);
    }

    @Override
    public List<Patient> getAllPatients() {
        return dao.getAllPatients();
    }

    @Override
    public void deletePatient(Patient patient) {
        dao.deletePatient(patient);
    }

    @Override
    public void deleteAllPatients() {
        dao.deleteAllPatients();
    }

    @Override
    public Patient getPatientByUUID(String uuid) {
        return dao.getPatientByUUID(uuid);
    }

    @Override
    public List<Patient> getPatientsByName(String name) {
        return dao.getPatientsByName(name);
    }
}