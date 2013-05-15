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
package com.mclinic.api.dao.impl;

import com.mclinic.api.dao.CredentialDao;
import com.mclinic.api.dao.MemberDao;
import com.mclinic.api.model.Member;
import com.mclinic.search.api.filter.Filter;
import com.mclinic.search.api.filter.FilterFactory;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl extends OpenmrsDaoImpl<Member> implements MemberDao {

    private static final String TAG = CredentialDao.class.getSimpleName();

    protected MemberDaoImpl() {
        super(Member.class);
    }

    /**
     * Get cohort by the name of the cohort. Passing empty string will returns all registered cohorts.
     *
     * @param cohortUuid the partial name of the cohort or empty string.
     * @return the list of all matching cohort on the cohort name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    public List<Member> getByCohortUuid(final String cohortUuid) throws ParseException, IOException {
        List<Filter> filters = new ArrayList<Filter>();
        if (!StringUtil.isEmpty(cohortUuid)) {
            Filter filter = FilterFactory.createFilter("cohortUuid", cohortUuid);
            filters.add(filter);
        }
        return service.getObjects(filters, daoClass);
    }

}
