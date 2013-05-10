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

import java.util.List;

public class User extends OpenmrsSearchable {

    private String uuid;

    private String givenName;

    private String middleName;

    private String familyName;

    private String username;

    private List<Privilege> privileges;

    private List<Role> roles;

    /**
     * Get the uuid of the user.
     *
     * @return the uuid of the user.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the uuid of the user.
     *
     * @param uuid the uuid of the user.
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     * Get the given name for the patient.
     *
     * @return the given name for the patient.
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Set the given name for the patient.
     *
     * @param givenName the given name for the patient.
     */
    public void setGivenName(final String givenName) {
        this.givenName = givenName;
    }

    /**
     * Get the middle name for the patient.
     *
     * @return the middle name for the patient.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Set the middle name for the patient.
     *
     * @param middleName the middle name for the patient.
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * Get the family name for the patient.
     *
     * @return the family name for the patient.
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Set the family name for the patient.
     *
     * @param familyName the family name for the patient.
     */
    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    /**
     * Get the username of the user.
     *
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user.
     *
     * @param username the username of the user.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Get the list of all privileges for the user.
     *
     * @return the list of all privileges for the user.
     */
    public List<Privilege> getPrivileges() {
        return privileges;
    }

    /**
     * Set the list of all privileges for the user.
     *
     * @param privileges the list of all privileges for the user.
     */
    public void setPrivileges(final List<Privilege> privileges) {
        this.privileges = privileges;
    }

    /**
     * Get the list of all roles for the user.
     *
     * @return the list of all roles for the user.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Set the list of all roles for the user.
     *
     * @param roles the list of all roles for the user.
     */
    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }
}
