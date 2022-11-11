package com.xwl.spring.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class MybatisConfig {
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setTypeAliasesPackage("com.xwl.spring.domain");
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean;
	}
	@Bean
	public MapperScannerConfigurer mapperFactoryBean() {

		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.xwl.spring.dao");
		return mapperScannerConfigurer;
	}
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager ds = new DataSourceTransactionManager();
		ds.setDataSource(dataSource);
		return ds;
	}
}
