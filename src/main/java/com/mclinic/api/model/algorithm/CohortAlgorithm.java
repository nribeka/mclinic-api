package com.mclinic.api.model.algorithm;

import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.Cohort;
import com.mclinic.search.api.serialization.Algorithm;

public class CohortAlgorithm implements Algorithm {

    /**
     * Implementation of this method will define how the object will be serialized from the String representation.
     *
     * @param json the string representation
     * @return the concrete object
     */
    @Override
    public Object deserialize(final String json) {
        Cohort cohort = new Cohort();

        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$.uuid");
        cohort.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$.display");
        cohort.setName(name);

        cohort.setJson(json);

        return cohort;
    }

    /**
     * Implementation of this method will define how the object will be de-serialized into the String representation.
     *
     * @param object the object
     * @return the string representation
     */
    @Override
    public String serialize(final Object object) {
        Cohort cohort = (Cohort) object;
        return cohort.getJson();
    }
}