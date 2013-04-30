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
package com.mclinic.api.service.impl;

import com.google.inject.Inject;
import com.mclinic.api.dao.AdministrativeDao;
import com.mclinic.api.service.AdministrativeService;

import java.io.File;
import java.io.IOException;

public class AdministrativeServiceImpl implements AdministrativeService {

    @Inject
    private AdministrativeDao dao;

    protected AdministrativeServiceImpl() {
    }

    @Override
    public void initializeRepository(final String resourcePath) throws IOException {
        dao.initializeRepository(resourcePath);
    }

    @Override
    public void initializeRepository(final File resourceDir) throws IOException {
        dao.initializeRepository(resourceDir);
    }
}
