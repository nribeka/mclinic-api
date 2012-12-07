package com.mclinic.api.dao.impl;

import java.util.List;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.RestAssuredService;
import com.burkeware.search.api.logger.Logger;
import com.burkeware.search.api.util.StringUtil;
import com.google.inject.Inject;
import com.mclinic.api.dao.PatientDao;
import com.mclinic.api.model.Patient;
import com.mclinic.util.Constants;

/**
 * This class should actually return actual objects from @SearchAPI
 *
 * @author Samuel Mbugua
 */
public class PatientDaoImpl implements PatientDao {

    @Inject
    private RestAssuredService service;

    @Inject
    private Logger log;

    private String TAG = "PatientDao";

    @Override
    public Patient createPatient(Patient patient) {
        try {
            service.createObject(patient, Context.getResource(Constants.PATIENT));
        } catch (Exception e) {
            log.debug(TAG, "Error creating patient " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        try {
            service.updateObject(patient, Context.getResource(Constants.PATIENT));
        } catch (Exception e) {
            log.debug(TAG, "Error updating patient " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Patient getPatientByIdentifier(String identifier) {
        try {
            return service.getObject("identifier: " + StringUtil.quote(identifier), Patient.class);
        } catch (Exception e) {
            log.debug(TAG, "Error in getPatientByIdentifier " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        try {
            return service.getObjects(null, Patient.class);
        } catch (Exception e) {
            log.debug(TAG, "Error in returning all patients " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void deletePatient(Patient patient) {
        try {
            service.invalidate(patient, Context.getResource(Constants.PATIENT));
        } catch (Exception e) {
            log.debug(TAG, "Error in delete patient " + e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteAllPatients() {
        // TODO Auto-generated method stub
    }

    @Override
    public Patient getPatientByUUID(String uuid) {
        try {
            return service.getObject("uuid: " + StringUtil.quote(uuid), Patient.class);
        } catch (Exception e) {
            log.debug(TAG, "Error in getPatientByUUID " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<Patient> getPatientsByName(String name) {
        try {
            return service.getObjects("name:" + StringUtil.quote(name + "*"), Patient.class);
        } catch (Exception e) {
            log.debug(TAG, "Error in getPatientByName " + e.getLocalizedMessage());
        }
        return null;
    }
}