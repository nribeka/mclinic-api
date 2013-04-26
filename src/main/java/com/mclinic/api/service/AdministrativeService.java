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
package com.mclinic.api.service;

import com.google.inject.ImplementedBy;
import com.mclinic.api.service.impl.AdministrativeServiceImpl;

import java.io.File;
import java.io.IOException;


/**
 * Service handling all administrative operations
 */
@ImplementedBy(AdministrativeServiceImpl.class)
public interface AdministrativeService {

    /**
     * Initialize the lucene repository with the resource configurations in the path.
     *
     * @param resourcePath the path where the resource configurations are stored.
     * @throws IOException when initialization failed.
     * @should register available algorithm classes.
     * @should register available resolver classes.
     * @should register available domain object classes.
     * @should load configuration file and register them.
     */
    void initializeRepository(final String resourcePath) throws IOException;

    /**
     * Initialize the lucene repository with the resource configurations in the path.
     *
     * @param resourceDir file pointing to the resource descriptors location.
     * @throws IOException when initialization failed.
     */
    void initializeRepository(final File resourceDir) throws IOException;
}
