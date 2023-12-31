package com.mega.vhplayer.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mega.vhplayer.beans.vo.BoardVO;
import com.mega.vhplayer.beans.vo.Criteria;

@Service
public interface BoardService {
	
	public void register(BoardVO board);
	public BoardVO get(Long bno);
	public boolean modify(BoardVO board);
	public boolean remove(Long bno);
	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
}
