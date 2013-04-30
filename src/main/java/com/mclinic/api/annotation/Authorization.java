package com.mclinic.api.annotation;

/**
 * TODO: Write brief description about the class here.
 */
public @interface Authorization {

    /**
     * List of all privileges allowed to execute this method.
     *
     * @return list of all privileges.
     */
    String[] privileges();
}
