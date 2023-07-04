package com.mega.vhplayer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.vhplayer.beans.dao.ReplyDAO;
import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.ReplyVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyServiceImp implements ReplyService {
	
	@Autowired
	private ReplyDAO dao;
	
	@Override
	public int register(ReplyVO reply) {
		log.info("register .............. : " + reply);
		return dao.register(reply);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get .............. : " + rno);
		return dao.get(rno);
	}

	@Override
	public int modify(ReplyVO reply) {
		log.info("modify .............. : " + reply);
		return dao.modify(reply);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove .............. : " + rno);
		return dao.remove(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("getList .............. : " + bno);
		return dao.getList(cri, bno);
	}
}
