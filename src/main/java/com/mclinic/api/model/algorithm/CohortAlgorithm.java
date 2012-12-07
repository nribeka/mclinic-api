package com.mclinic.api.model.algorithm;

import com.burkeware.search.api.serialization.Algorithm;
import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.Cohort;

public class CohortAlgorithm implements Algorithm {
    @Override
    public Cohort deserialize(final String json) {
        Cohort cohort = new Cohort();

        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$.uuid");
        cohort.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$.display");
        cohort.setName(name);

//        String description = JsonPath.read(jsonObject, "$.description");
//        cohort.setDescription(description);

        cohort.setJson(json);

        return cohort;
    }

    @Override
    public String serialize(final Object object) {
        Cohort cohort = (Cohort) object;
        return cohort.getJson();
    }
}