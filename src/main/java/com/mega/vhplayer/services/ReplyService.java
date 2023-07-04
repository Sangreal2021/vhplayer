package com.mega.vhplayer.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.ReplyVO;

@Service
public interface ReplyService {

	public int register(ReplyVO reply);
	public ReplyVO get(Long rno);
	public int modify(ReplyVO reply);
	public int remove(Long rno);
	public List<ReplyVO> getList(Criteria cri, Long bno);
}
