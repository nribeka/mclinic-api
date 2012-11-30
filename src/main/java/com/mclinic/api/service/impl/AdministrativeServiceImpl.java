package com.mclinic.api.service.impl;

import com.google.inject.Inject;
import com.mclinic.api.dao.AdministrativeDao;
import com.mclinic.api.service.AdministrativeService;

import java.io.File;

public class AdministrativeServiceImpl implements AdministrativeService {
	
	@Inject
    private AdministrativeDao dao;
	
	@Override
	public void initializeDB(File j2lFile) {
		dao.initializeDB(j2lFile);
	}
	
	@Override
	public void loadPatients(File jsonFilesDir) {
		dao.loadPatients(jsonFilesDir);
	}

	@Override
	public void loadCohorts(File jsonFilesDir) {
		dao.loadCohorts(jsonFilesDir);
	}

	@Override
	public void downloadCohorts() {
		dao.downloadCohorts();
	}
	
	@Override
	public void loadCohortPatients(File jsonFilesDir) {
		dao.loadCohortPatients(jsonFilesDir);
	}

	@Override
	public void downloadCohortPatients(String cohortUUID) {
		dao.downloadCohortPatients(cohortUUID);
	}

	@Override
	public void loadObservations(File jsonFilesDir) {
		dao.loadObservations(jsonFilesDir);
	}

	@Override
	public void downloadObservations(String patientUUID) {
		dao.downloadObservations(patientUUID);
	}
}