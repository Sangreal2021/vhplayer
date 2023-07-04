package com.mega.vhplayer.beans.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.ReplyVO;
import com.mega.vhplayer.mappers.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReplyDAO {

	@Autowired
	private ReplyMapper mapper;
	
	// 등록
	public int register(ReplyVO reply) {
		log.info("register ............. : " + reply);
		return mapper.insert(reply);
	}
	
	// 단일 조회(rno로 조회)
	public ReplyVO get(Long rno) {
		log.info("get ............. : " + rno);
		return mapper.read(rno);
	}
	
	// 수정
	public int modify(ReplyVO reply) {
		log.info("modify ............. : " + reply);
		return mapper.update(reply);
	}
	
	// 삭제
	public int remove(Long rno) {
		log.info("remove ............. : " + rno);
		return mapper.delete(rno);
	}
	
	// 댓글 목록 조회
	public List<ReplyVO> getList(Criteria cri, Long bno){
		log.info("get Reply List ............. : " + bno);
		cri.setParam();
		return mapper.getListWithPaging(cri, bno);
	}
}
