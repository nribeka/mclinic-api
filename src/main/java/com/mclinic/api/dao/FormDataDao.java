package com.mclinic.api.dao;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.FormDataDaoImpl;
import com.mclinic.api.model.FormData;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * TODO: Write brief description about the class here.
 */
@ImplementedBy(FormDataDaoImpl.class)
public interface FormDataDao extends LocalDao<FormData> {

    /**
     * Get all searchable form data with a particular status.
     *
     * @param patientUuid the patient's uuid associated to this form data.
     * @param userUuid    user's uuid associated to this form data.
     * @param status      the status of this form data.
     * @return list of all searchable object or empty list.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    List<FormData> getAll(final String patientUuid, final String userUuid, final String status)
            throws ParseException, IOException;
}