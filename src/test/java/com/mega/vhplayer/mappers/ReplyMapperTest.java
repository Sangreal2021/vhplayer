package com.mega.vhplayer.mappers;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.ReplyVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class ReplyMapperTest {
	
	@Autowired
	private ReplyMapper mapper;
	
//	@Test
	public void testMapper() {
		log.info("----------- Mapper Test -----------");
		log.info(mapper.toString());
	}
	
	// tbl_board에 실재 존재하는 5개의 글들을 선택
	private Long[] arrBno = {13L, 12L, 11L, 10L, 9L};
	
	@Test
	public void testInsert() {
		
		/*
		※ <참고> IntStream(기본형 특화 스트림) 클래스에 range(), rangeClosed()
		1) rangeClosed()
			- 특정 범위의 숫자를 차례대로 생성해주는 기능
			- 시작 값과 종료 값을 인수로 받는다
			- 종료값을 포함해서 반환
		2) range()
			- 특정 범위의 숫자를 차례대로 생성해주는 기능
			- 시작 값과 종료 값을 인수로 받는다
			- 종료값을 포함하지 않고 반환
		*/
		
		// 5개의 게시글에서 2개씩 댓글을 달아봅시다
		// 1개씩 꺼내서 10번 돌려라..
		IntStream.rangeClosed(0, 9).forEach(i -> {
			// 1개 돌때마다 ReplyVO 생성
			ReplyVO reply = new ReplyVO();
			
			reply.setBno(arrBno[i % 5]);
			reply.setReply("댓글 자동생성" + i);
			reply.setUser_name("Bot.A" + i);
			
			mapper.insert(reply);
		});
	}
	
//	@Test
	public void testRead() {
		Long targetRno = 1L;
		ReplyVO reply = mapper.read(targetRno);
		
		log.info("reply ok -------------------------------");
		log.info(reply.toString());
	}
	
//	@Test
	public void testDelete() {
		Long targetRno = 10L;
		int deleteCount = mapper.delete(targetRno);
		
		log.info("reply delete ---------------------------");
		log.info("delete count : " + deleteCount);
	}
	
//	@Test
	public void testUpdate() {
		Long targetRno = 9L;
		
		// read()를 하는 이유는 내용만 update하기 위해!!
		ReplyVO reply = mapper.read(targetRno);
		reply.setReply("Update했음!");
		
		int updateCount = mapper.update(reply);
		log.info("reply update --------------------------");
		log.info("update count : " + updateCount);
	}
	
//	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		// arrBno[1] = 12L
		// arrBno[4] 하면 결과가 1개 나옴(하나는 위에서 지웠으므로)
		List<ReplyVO> replies = mapper.getListWithPaging(cri, arrBno[1]);
		log.info("reply list ----------------------------");
		replies.forEach(reply -> log.info(reply.toString()));
	}
	
//	@Test
	public void testGetTest() {
		ReplyVO reply = mapper.getTest(2L, 12L);
		
		log.info("reply ok ------------------------");
		log.info(reply.toString());
	}
}




























