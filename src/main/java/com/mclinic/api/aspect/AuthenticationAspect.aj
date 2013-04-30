package com.mclinic.api.aspect;

/**
 * TODO: Write brief description about the class here.
 */
public aspect AuthenticationAspect {
    pointcut publicMethodExecuted(): execution(@com.mclinic.api.annotation.Authorization * *(..));

    before(): publicMethodExecuted() {
        System.out.printf("Entering method: %s. \n", thisJoinPoint.getSignature());
    }
}
