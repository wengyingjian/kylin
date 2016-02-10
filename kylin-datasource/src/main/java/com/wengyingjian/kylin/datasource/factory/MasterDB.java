package com.wengyingjian.kylin.datasource.factory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Component
@Configuration
@EnableTransactionManagement
public class MasterDB implements TransactionManagementConfigurer {

    private static Logger logger = LoggerFactory.getLogger(MasterDB.class);
    @Autowired
    private DatasourceProperties datasourceProperties;

    public MasterDB() {
    }

    public SqlSessionTemplate getSqlSession() {
        try {
            return new SqlSessionTemplate(masterSqlSessionFactoryBean().getObject());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Bean
    @Scope("prototype")
    SqlSessionTemplate masterSqlSessionTemplate() {
        return this.getSqlSession();
    }

    @Bean
    SqlSessionFactoryBean masterSqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(masterDataSource());
        return sqlSessionFactory;
    }

    @Bean
    DataSource masterDataSource() {
        PoolProperties p = DBHelper.buildPoolProperties(datasourceProperties.getMaster());
        p.setLogAbandoned(true);
        p.setDefaultAutoCommit(true);
        return new DataSource(p);
    }

    @Bean
    DataSource dataSource() {
        return masterDataSource();
    }

    @Bean
    PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }

}
