package com.mclinic.api.aspect;

import com.mclinic.api.annotation.Authorization;
import com.mclinic.api.context.Context;
import com.mclinic.api.context.ContextFactory;
import com.mclinic.api.model.User;
import org.aspectj.lang.Signature;

import java.io.IOException;

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

        try {
            Context context = ContextFactory.createContext();
            if (!context.isAuthenticated()) {
                User user = context.getAuthenticatedUser();
                System.out.println("Context is not authenticated!");
                System.out.println("Authenticated user is: " + user.getUsername());
            } else {
                System.out.println("Context is authenticated!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
