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

/**
 * TODO: Write brief description about the class here.
 */
public class Privilege extends OpenmrsSearchable {

    private String uuid;

    private String name;

    private String checksum;

    /**
     * Get the name associated with this privilege.
     *
     * @return the name associated with this privilege.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name associated with this privilege.
     *
     * @param name the name associated with this privilege.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the uuid of this privilege.
     *
     * @return the uuid of this privilege.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the uuid of this privilege.
     *
     * @param uuid the uuid of this privilege.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
