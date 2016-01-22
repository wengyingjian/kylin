/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.datasource.factory;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 2.1 15/12/07
 * @since 1.0
 */
@Configuration
@Component
public class AutoMapperScannerConfigurator implements EnvironmentAware {

    private Environment environment;

    @Bean
    MapperScannerConfigurer masterMapperScannerConfigurer() throws Exception {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(environment.getProperty(DatasourceProperties.PLACEHOLDER_NAME_MASTER_MAPPERS_PATH, ""));
        configurer.setSqlSessionTemplateBeanName("masterSqlSessionTemplate");
        configurer.setNameGenerator(new MapperBeanNameGenerator("master"));
        return configurer;
    }

    @Bean
    MapperScannerConfigurer slaveMapperScannerConfigurer() throws Exception {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(environment.getProperty(DatasourceProperties.PLACEHOLDER_NAME_SLAVE_MAPPERS_PATH, ""));
                configurer.setSqlSessionTemplateBeanName("slaveSqlSessionTemplate");
        configurer.setNameGenerator(new MapperBeanNameGenerator("slave"));
        return configurer;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
