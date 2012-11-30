package com.mclinic.api.service;

import java.io.File;

import com.google.inject.ImplementedBy;
import com.mclinic.api.service.impl.AdministrativeServiceImpl;


/**
 * Service handling all administrative operations
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
@ImplementedBy (AdministrativeServiceImpl.class)
public interface AdministrativeService {
	
	/**
	 * @param j2lFile the j2l file to use to convert json files into lucene objects
	 */
	void initializeDB(File j2lFile);
	
	/** Load all patients in to the lucene index. This method will load all <code>Patient</code> as represented
	 *  by all json files in @jsonFilesDir directory into the lucene search index engine. <br><br>
	 *  
	 *  The method should be called once in the life time of a project. If new json files have been added to
	 *  the Json directory then update should be the method to call. <br><br>
	 *  
	 *  @param jsonFilesDir the directory containing all patients json files. 
	 */
	void loadPatients(File jsonFilesDir);
	
	void loadCohorts(File jsonFilesDir);
	
	void downloadCohorts();

	void loadCohortPatients(File jsonFilesDir);
	
	void downloadCohortPatients(String cohortUUID);
	
	void loadObservations(File jsonFilesDir);

	void downloadObservations(String patientUUID);
}