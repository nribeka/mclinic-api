package com.mclinic.api.dao;

import com.mclinic.search.api.model.object.BaseSearchable;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * TODO: Write brief description about the class here.
 */
public interface LocalDao<T extends BaseSearchable> extends SearchableDao<T> {
    /**
     * Save the object to the local repository.
     *
     * @param object   the object to be saved.
     * @param resource the resource descriptor used for saving.
     * @return saved object.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void save(final T object, final String resource) throws ParseException, IOException;

    /**
     * Update the saved object in the local repository.
     *
     * @param object   the object to be updated.
     * @param resource the resource descriptor used for saving.
     * @return updated object.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void update(final T object, final String resource) throws ParseException, IOException;

    /**
     * Get the OpenMRS searchable object using the uuid.
     *
     * @param uuid the uuid of the searchable object.
     * @return the searchable object.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    T getByUuid(final String uuid) throws ParseException, IOException;

    /**
     * Get cohort by the name of the cohort. Passing empty string will returns all registered cohorts.
     *
     * @param name the partial name of the cohort or empty string.
     * @return the list of all matching cohort on the cohort name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    public List<T> getByName(final String name) throws ParseException, IOException;

    /**
     * Delete the searchable object from the lucene repository.
     *
     * @param searchable the object to be deleted.
     * @param resource   the resource descriptor used to retrieve the object from the repository.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void delete(final T searchable, final String resource) throws ParseException, IOException;
}
