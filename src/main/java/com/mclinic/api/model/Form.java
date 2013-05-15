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
 * A form is a class to hold form reference in the server. Each form will have one to one connection with a
 * FormTemplate.
 */
public class Form extends OpenmrsSearchable {

    private String uuid;

    private String name;

    /**
     * Get the uuid for the cohort.
     *
     * @return the uuid.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the uuid for the cohort.
     *
     * @param uuid the uuid to set.
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     * Get the name for the cohort.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name for the cohort.
     *
     * @param name the name to set.
     */
    public void setName(final String name) {
        this.name = name;
    }
}
