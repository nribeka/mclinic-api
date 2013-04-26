package com.mclinic.api.dao;

import com.mclinic.search.api.model.object.BaseSearchable;
import com.mclinic.search.api.resource.Resource;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * TODO: Write brief description about the class here.
 */
public interface SearchableDao<T extends BaseSearchable> {

    /**
     * Delete the searchable object from the lucene repository.
     *
     * @param searchable the object to be deleted.
     * @param resource   the resource descriptor used to retrieve the object from the repository.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void delete(final T searchable, final Resource resource) throws ParseException, IOException;

    /**
     * Get all searchable object for the particular type.
     *
     * @return list of all searchable object or empty list.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    List<T> getAll() throws ParseException, IOException;

    /**
     * Delete all searchable object from the lucene repository.
     *
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    void deleteAll() throws ParseException, IOException;
}
