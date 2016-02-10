package com.wengyingjian.kylin.datasource.factory;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.util.Properties;

@Configuration
@Component
public class AutoMapperScannerConfigurator {

    @Bean
    Properties datasourceConf() throws Exception {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("datasource.properties"));
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    MapperScannerConfigurer masterMapperScannerConfigurer() throws Exception {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(datasourceConf().getProperty("datasource.master.mappersPath"));
        configurer.setSqlSessionTemplateBeanName("masterSqlSessionTemplate");
        configurer.setNameGenerator(new MapperBeanNameGenerator("master"));
        return configurer;
    }

    @Bean
    MapperScannerConfigurer slaveMapperScannerConfigurer() throws Exception {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(datasourceConf().getProperty("datasource.slave.mappersPath"));
        configurer.setSqlSessionTemplateBeanName("slaveSqlSessionTemplate");
        configurer.setNameGenerator(new MapperBeanNameGenerator("slave"));
        return configurer;
    }

}
