/*
 * Copyright 2015 © 59store.com.
 *
 * RemoteService.java
 *
 */
package com.wengyingjian.kylin.rpc.server.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;



/**
 * Created by shanren on 7/15/15.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RemoteService {

    ServiceType serviceType() default ServiceType.HTTP;

    Class<?> serviceInterface();

    String exportPath();

}
