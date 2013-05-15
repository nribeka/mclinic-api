package com.mclinic.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: Write brief description about the class here.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Authorization {

    /**
     * List of all privileges allowed to execute this method.
     *
     * @return list of all privileges.
     */
    String[] privileges();
}
