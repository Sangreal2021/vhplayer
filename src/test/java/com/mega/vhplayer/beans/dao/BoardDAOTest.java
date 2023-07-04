package com.mega.vhplayer.beans.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mega.vhplayer.beans.vo.BoardVO;
import com.mega.vhplayer.beans.vo.Criteria;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class BoardDAOTest {
	
	@Autowired
	BoardDAO dao;
	
//	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		
		board.setTitle("dao_test01");
		board.setContent("dao_test Content01");
		board.setUser_name("dao_tester01");
		board.setUser_auth("1");
		
		dao.register(board);
		
		log.info("[Register BNO] " + board.getBno());
	}
	
	@Test
	public void testGet() {
		Long bno = 4L;
		log.info("[testGet] " + dao.get(bno).toString());
	}
	
//	@Test
	public void testGetList() {
		dao.getList(new Criteria(0,5))
			.forEach(board -> log.info("******* " + board.toString()));
	}
	
//	@Test
	public void testModify() {
		BoardVO board = dao.get(1L);
		
		if(board == null) {
			log.info("[testModify] Target Not Found!!");
			return;
		}
		board.setTitle("dao_modified");
		board.setUser_name("dao_tester01_modi");
		board.setUser_auth("2");
		
		log.info("[testModify] " + (dao.modify(board) ? "성공" : "실패"));
	}
	
//	@Test
	public void testRemove() {
		Long bno = 5L;
		BoardVO board = dao.get(bno);
		
		if(board == null) {
			log.info("[testRemove] Target Not Found!!");
			return;
		}
		log.info("[testRemove] " + (dao.remove(bno)? "성공" : "실패"));
	}
}




















