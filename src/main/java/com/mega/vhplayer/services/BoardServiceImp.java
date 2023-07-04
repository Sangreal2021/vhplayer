package com.mega.vhplayer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.vhplayer.beans.dao.BoardDAO;
import com.mega.vhplayer.beans.vo.BoardVO;
import com.mega.vhplayer.beans.vo.Criteria;

@Service
public class BoardServiceImp implements BoardService {
	
	@Autowired
	private BoardDAO dao;

	@Override
	public void register(BoardVO board) {
		dao.register(board);
	}

	@Override
	public BoardVO get(Long bno) {
		return dao.get(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		return dao.modify(board);
	}

	@Override
	public boolean remove(Long bno) {
		return dao.remove(bno);
	}

	@Override
	public List<BoardVO> getList() {
		return dao.getList();
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return dao.getList(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return dao.getTotal(cri);
	}
}
