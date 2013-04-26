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

import com.mclinic.search.api.model.object.BaseSearchable;

/**
 * TODO: Write brief description about the class here.
 */
public class FormData extends BaseSearchable {

    private String uuid;

    private String status;

    private String payload;

    private String patientUuid;

    private String userUuid;

    private String templateUuid;

    private String checksum;

    /**
     * Get the form data uuid.
     *
     * @return the form data uuid.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the form data uuid.
     *
     * @param uuid the form data uuid.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Get the filling status for the form.
     *
     * @return the filling status for the form.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the filling status for the form.
     *
     * @param status the filling status for the form.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the payload of the form data.
     *
     * @return the payload of the form data.
     */
    public String getPayload() {
        return payload;
    }

    /**
     * Set the payload of the form data.
     *
     * @param payload the payload of the form data.
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * Get the uuid of the patient associated with this form data.
     *
     * @return the uuid of the patient associated with this form data.
     */
    public String getPatientUuid() {
        return patientUuid;
    }

    /**
     * Set the uuid of the patient associated with this form data.
     *
     * @param patientUuid the uuid of the patient associated with this form data.
     */
    public void setPatientUuid(final String patientUuid) {
        this.patientUuid = patientUuid;
    }

    /**
     * Get the uuid of the user who filled this form data
     *
     * @return the uuid of the user who filled this form data
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * Set the uuid of the user who filled this form data
     *
     * @param userUuid the uuid of the user who filled this form data
     */
    public void setUserUuid(final String userUuid) {
        this.userUuid = userUuid;
    }

    /**
     * Get the form data template uuid.
     *
     * @return the form data template uuid.
     */
    public String getTemplateUuid() {
        return templateUuid;
    }

    /**
     * Set the form data template uuid.
     *
     * @param templateUuid the form data template uuid.
     */
    public void setTemplateUuid(String templateUuid) {
        this.templateUuid = templateUuid;
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
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
