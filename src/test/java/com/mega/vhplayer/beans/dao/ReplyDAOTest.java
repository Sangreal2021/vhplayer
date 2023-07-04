package com.mega.vhplayer.beans.dao;

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
public class ReplyDAOTest {
	
	@Autowired
	private ReplyDAO dao;
	
	private Long[] arrBno = {13L, 12L, 11L, 10L, 9L};
	
	@Test
	public void testRegister() {
		// 5개의 게시글에 2개씩 댓글 달기
		// 1개씩 꺼내서 10번 돌려라..
		IntStream.rangeClosed(0, 9).forEach(i -> {
			// 1개 돌 때마다 ReplyVO 생성
			ReplyVO reply = new ReplyVO();
			
			reply.setBno(arrBno[i % 5]);
			reply.setReply("댓글 자동생성" + i);
			reply.setUser_name("Bot.DAO" + i);
			
			dao.register(reply);
		});
	}
	
//	@Test
	public void testRead() {
		Long targetRno = 11L;
		ReplyVO reply = dao.get(targetRno);
		
		log.info("dao - get -----------------------");
		log.info(reply.toString());
	}
	
//	@Test
	public void testDelete() {
		Long targetRno = 10000L;
		
		int deleteCount = dao.remove(targetRno);
		log.info("dao - delete -----------------------");
		log.info("delete count : " + deleteCount);
	}
	
//	@Test
	public void testUpdate() {
		Long targetRno = 11L;
		
		ReplyVO reply = dao.get(targetRno);
		reply.setReply("DAO_01 수정했음");
		
		int updateCount = dao.modify(reply);
		log.info("dao - modify -----------------------");
		log.info("modify count : " + updateCount);
	}
	
//	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		// arrBno[2] : bno가 11인 댓글
		List<ReplyVO> replies = dao.getList(cri, arrBno[2]);
		log.info("dao - getList -----------------------");
		// replies에서 꺼내서 reply에 담아서 돌리겠다..(x로 바꿔도 됨)
		replies.forEach(reply -> log.info(reply.toString()));
	}
}




























