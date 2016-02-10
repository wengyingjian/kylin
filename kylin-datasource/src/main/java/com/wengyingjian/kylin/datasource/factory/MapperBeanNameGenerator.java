package com.wengyingjian.kylin.datasource.factory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

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
