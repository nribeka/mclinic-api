/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package com.mclinic.api.model;

/**
 * The Member class will reference to uuid of all patients for which the Member is associated with.
 * <br/>
 * This class is an exception from all of the other model classes where it's a local object but have the name similar
 * with the same remote resource. This class must not be associated with the <code>MemberCohortResolver</code>.
 */
public class Member extends OpenmrsSearchable {

    private String cohortUuid;

    private String patientUuid;

    /**
     * Get the cohort uuid associated with this cohort member.
     *
     * @return the cohort uuid associated with this cohort member.
     */
    public String getCohortUuid() {
        return cohortUuid;
    }

    /**
     * Set the cohort uuid associated with this cohort member.
     *
     * @param cohortUuid the cohort uuid associated with this cohort member.
     */
    public void setCohortUuid(final String cohortUuid) {
        this.cohortUuid = cohortUuid;
    }

    /**
     * Get the uuid of the patient.
     *
     * @return the uuid of the patient.
     */
    public String getPatientUuid() {
        return patientUuid;
    }

    /**
     * Set the uuid of the patient.
     *
     * @param patientUuid the the uuid of the patient.
     */
    public void setPatientUuid(final String patientUuid) {
        this.patientUuid = patientUuid;
    }
}
