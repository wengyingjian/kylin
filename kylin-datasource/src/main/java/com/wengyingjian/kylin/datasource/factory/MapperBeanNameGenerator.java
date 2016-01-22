/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.datasource.factory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * The bean name generator of mybatis mapper.
 *
 * @author <a href="mailto:zhuzm@59store.com">天河</a>
 * @version 1.0 2015年9月11日
 * @since 1.0
 */
public class MapperBeanNameGenerator implements BeanNameGenerator {
    private String prefix;

    /**
     * @param prefix
     */
    public MapperBeanNameGenerator(String prefix) {
        Assert.notNull(prefix, "Prefix must not be null");
        this.prefix = prefix;
    }

    /**
     * @see BeanNameGenerator#generateBeanName(BeanDefinition,
     *      BeanDefinitionRegistry)
     */
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        return prefix + shortClassName;
    }

}
