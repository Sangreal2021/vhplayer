package com.mega.vhplayer.services;

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
public class BoardServiceTest {
	
	@Autowired
	BoardServiceImp service;
	
//	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("ser_test01");
		board.setContent("ser Content01");
		board.setUser_name("serv_tester01");
		board.setUser_auth("1");
		
		service.register(board);
		log.info("[testRegister] " + board.getBno());
	}
	
//	@Test
	public void testGet() {
		log.info("[testGet] " + service.get(3L).toString());
	}
	
//	@Test
	public void testGetList() {
//		boardService.getList().forEach(board -> log.info(board.toString()));
		service.getList(new Criteria(0,6))
					.forEach(board -> log.info("***** " + board.toString()));
	}
	
//	@Test
	public void testModify() {
		BoardVO board = service.get(3L);
		
		if(board == null) {
			return;
		}
		board.setTitle("service Modi");
		board.setUser_name("service tester02");
		board.setUser_auth("2");
		
		log.info("[testModify] " + service.modify(board));
	}
	
//	@Test
	public void testRemove() {
		Long bno = 5L;
		BoardVO board = service.get(bno);
		
		if(board == null) {
			return;
		}
		log.info("[testRemove] " + service.remove(bno));
	}
}














