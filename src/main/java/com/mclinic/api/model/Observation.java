/**
 * Copyright 2012 Muzima Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mclinic.api.model;

import com.mclinic.search.api.util.StringUtil;

import java.util.Date;

public class Observation extends OpenmrsSearchable {

    private String uuid;

    private String patientUuid;

    private String value = StringUtil.EMPTY;

    private Date observationDate;

    private Integer dataType;

    private String questionName;

    private String questionUuid;

    private String checksum;

    /**
     * Get the uuid for the observation.
     *
     * @return the uuid for the observation.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the uuid for the observation.
     *
     * @param uuid the uuid for the observation.
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     * Get the uuid for the patient.
     *
     * @return the uuid for the patient.
     */
    public String getPatientUuid() {
        return patientUuid;
    }

    /**
     * Set the uuid for the patient.
     *
     * @param patientUuid the uuid for the patient.
     */
    public void setPatientUuid(final String patientUuid) {
        this.patientUuid = patientUuid;
    }

    /**
     * Get the date of the observation.
     *
     * @return the date of the observation.
     */
    public Date getObservationDate() {
        return observationDate;
    }

    /**
     * Set the date of the observation.
     *
     * @param observationDate the date of the observation.
     */
    public void setObservationDate(final Date observationDate) {
        this.observationDate = observationDate;
    }

    /**
     * Get the data type of the observation.
     *
     * @return the data type of the observation.
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * Set the data type of the observation.
     *
     * @param dataType the data type of the observation.
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * Get the question name of the observation.
     *
     * @return the question name of the observation.
     */
    public String getQuestionName() {
        return questionName;
    }

    /**
     * Set the question name of the observation.
     *
     * @param questionName the question name of the observation.
     */
    public void setQuestionName(final String questionName) {
        this.questionName = questionName;
    }

    /**
     * Get the uuid of the question of the observation.
     *
     * @return the uuid of the question of the observation.
     */
    public String getQuestionUuid() {
        return questionUuid;
    }

    /**
     * Set the uuid of the question of the observation.
     *
     * @param questionUuid the uuid of the question of the observation.
     */
    public void setQuestionUuid(final String questionUuid) {
        this.questionUuid = questionUuid;
    }

    /**
     * Get the value of the observation.
     *
     * @return the value of the observation.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value of the observation.
     *
     * @param value the value of the observation.
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Get the checksum for the searchable object.
     *
     * @return the searchable object's checksum.
     */
    @Override
    public String getChecksum() {
        return checksum;
    }

    /**
     * Set the checksum for the searchable object.
     *
     * @param checksum the checksum for the searchable object.
     */
    @Override
    public void setChecksum(final String checksum) {
        this.checksum = checksum;
    }
}
