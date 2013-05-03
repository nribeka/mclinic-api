package com.mclinic.api.aspect;

import com.mclinic.api.annotation.Authorization;
import com.mclinic.api.context.Context;
import com.mclinic.api.context.ContextFactory;
import org.aspectj.lang.Signature;

/**
 * TODO: Write brief description about the class here.
 */
public aspect AuthenticationAspect {

    pointcut serviceMethod(Authorization authorization): execution(@Authorization * *(..))
            && @annotation(authorization);

    before(Authorization authorization): serviceMethod(authorization) {
        Signature signature = thisJoinPoint.getSignature();
        System.out.printf("Entering method: %s. \n", signature);
        System.out.printf("Annotation class: %s. \n", authorization);
        for (String privilege : authorization.privileges()) {
            System.out.printf("Privilege: %s.\n", privilege);
        }

        Context context = ContextFactory.createContext();
        if (!context.isAuthenticated())
            System.out.println("Context is not authenticated!");
        else
            System.out.println("Context is authenticated!");

    }
}
