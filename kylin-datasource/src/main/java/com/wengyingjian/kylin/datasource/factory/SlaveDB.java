/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
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
import org.springframework.stereotype.Component;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 2.0 15/11/3
 * @since 2.0
 */
@Component
@Configuration
public class SlaveDB {

	private static Logger logger = LoggerFactory.getLogger(MasterDB.class);
	@Autowired
	private DatasourceProperties datasourceProperties;

	public SlaveDB() {
	}

	public SqlSessionTemplate getSqlSession() {
		try {
			return new SqlSessionTemplate(slaveSqlSessionFactoryBean().getObject());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Bean
	@Scope("prototype")
	SqlSessionTemplate slaveSqlSessionTemplate() {
		return this.getSqlSession();
	}

	@Bean
	SqlSessionFactoryBean slaveSqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(slaveDataSource());
		return sqlSessionFactory;
	}

	@Bean
	DataSource slaveDataSource() {
		PoolProperties p = DBHelper.buildPoolProperties(datasourceProperties.getSlave());
		p.setDefaultReadOnly(true);
		p.setLogAbandoned(true);
		return new DataSource(p);
	}

}
