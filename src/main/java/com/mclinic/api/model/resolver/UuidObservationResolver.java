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
package com.mclinic.api.model.resolver;


import java.io.IOException;

public class UuidObservationResolver extends BaseOpenmrsResolver {

    private static final String REPRESENTATION =
            "?v=custom:(uuid,obsDatetime,concept.datatype.conceptDatatypeId,concept.name.name,concept.uuid," +
                    "person.uuid,encounter.uuid,location.uuid,location.name,valueCoded:ref,valueNumeric,valueDatetime)";

    /**
     * Return the full REST resource based on the search string passed to the method.
     *
     * @param searchString the search string
     * @return full URI to the REST resource
     */
    @Override
    public String resolve(String searchString) throws IOException {
        return getConfiguration().getServer() + "/ws/rest/v1/obs/" + searchString + REPRESENTATION;
    }
}
