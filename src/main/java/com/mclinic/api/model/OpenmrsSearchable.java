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

import com.mclinic.search.api.model.object.BaseSearchable;

/**
 */
public abstract class OpenmrsSearchable extends BaseSearchable {

    private String uri;

    /**
     * Get the openmrs object's uri.
     *
     * @return the uri;
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set the openmrs object's uri.
     *
     * @param uri the uri.
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }
}
