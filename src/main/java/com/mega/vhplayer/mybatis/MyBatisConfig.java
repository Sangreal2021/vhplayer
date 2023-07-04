package com.mega.vhplayer.mybatis;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
@MapperScan("com.mega.vhplayer.mappers")
public class MyBatisConfig {
	
	private final ApplicationContext applicationContext;
	
	// 1. Property 가져오기(application.properties에서 접두사가 spring.datasource.hikari인 데이터)
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariconfig() {
		return new HikariConfig();
	}
	
	// 2. DataSource 설정
	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariconfig());
	}
	
	// 3. SQLSessionFactory 만들기
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource());
		
		// SQL query를 작성할 xml 경로 설정
		sfb.setMapperLocations(applicationContext.getResources("classpath*:/mappers/*.xml"));
		sfb.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml"));
		
		try {
			// 위에서 지정한 것을 공장에 투입
			SqlSessionFactory factory = sfb.getObject();
//			factory.getConfiguration().setMapUnderscoreToCamelCase(true);
			return factory;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}


















