package com.mega.vhplayer.mappers;

import java.util.List;

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
public class BoardMapperTest {
	
	@Autowired
	private BoardMapper mapper;
	
//	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		
		cri.setAmount(5); // 한 페이지에 넣을 게시글 수
		cri.setPageNum(0); // 실제 보여줄 페이지 -1
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info("--------- " + board.getBno() + ""));
	}
	
	// 전체 리스트 조회
//	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board.toString()));
	}
	
//	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("test01");
		board.setContent("test Content");
		board.setUser_name("tester01");
		board.setUser_auth("1");
		
		mapper.insert(board);
	}
	
//	@Test
	public void testInsertSelectKey_bno() {
		BoardVO board = new BoardVO();
		board.setTitle("test_key01");
		board.setContent("test key_bno Content");
		board.setUser_name("tester_key01");
		board.setUser_auth("2");
		
		mapper.insertSelectKey_bno(board);
	}
	
//	@Test
	public void testRead() {
		long bno = 2L;
		log.info(mapper.read(bno).toString());
	}
	
//	@Test
	public void testDelete() {
		long bno = 2L;
		log.info("[Delete Count] : " + mapper.delete(bno));
	}
	
	@Test
	public void testUpdate() {
		Long bno = 3L;
		
		if(mapper.read(bno) != null) {
			BoardVO board = new BoardVO();
			board.setBno(3L);
			board.setTitle("수정된 제목");
			board.setContent("수정된 내용");
			board.setUser_name("tester_key01_modi");
			board.setUser_auth("1");
			
			log.info("[Update Count] " + mapper.update(board));
		}else {
			log.info("[Update Count] No Such Board!");
		}
	}
}






















