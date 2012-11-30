package com.mclinic.api.service.impl;

import com.google.inject.Inject;
import com.mclinic.api.dao.CohortDao;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.service.CohortService;

import java.util.List;

public class CohortServiceImpl implements CohortService {
	
	@Inject
	private CohortDao dao;

	@Override
	public Cohort createCohort(Cohort cohort) {
		return dao.createCohort(cohort);
	}

	@Override
	public Cohort updateCohort(Cohort cohort) {
		return dao.updateCohort(cohort);
	}

	@Override
	public Cohort getCohortByUUID(String uuid) {
		return dao.getCohortByUUID(uuid);
	}
	
	@Override
	public List<Cohort> getCohortsByName(String name) {
		return dao.getCohortsByName(name);
	}

	@Override
	public List<Cohort> getAllCohorts() {
		return dao.getAllCohorts();
	}

	@Override
	public void deleteCohort(Cohort cohort) {
		dao.deleteCohort(cohort);
	}

	@Override
	public void deleteAllCohorts() {
		dao.deleteAllCohorts();
	}
}