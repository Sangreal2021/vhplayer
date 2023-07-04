package com.mega.vhplayer.mybatis;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class MyBatisConfigTest {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	// dataSource 테스트
	@Test
	public void testDataSource() {
		log.info("Datasource : " + dataSource);
		
		try 
			(
				Connection conn = dataSource.getConnection();
			)
		{
			log.info("-------------------------------");
			log.info("datasource connection : " + conn);
			log.info("-------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// SQL 테스트 : sqlSessionFactory가 있는지?(null이 아닌지)
	@Test
	public void testSQLSession() {
		log.info("------------------------------------------");
		log.info("sql session factory : " + sqlSessionFactory);
		log.info("------------------------------------------");
		
		try 
			(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
				Connection conn = sqlSession.getConnection(); // DB 연결
			)
		{
			log.info("sql session : " + sqlSession);
			log.info("sql session conn : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



























