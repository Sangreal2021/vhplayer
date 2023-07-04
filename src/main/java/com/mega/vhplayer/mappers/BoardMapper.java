package com.mega.vhplayer.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mega.vhplayer.beans.vo.BoardVO;
import com.mega.vhplayer.beans.vo.Criteria;

@Mapper
public interface BoardMapper {
	
	// 게시글 개수
	public int getTotal(Criteria cri);
	
	// 게시글 목록(페이징 처리)
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 게시글 전체 목록
	public List<BoardVO> getList();
	
	// 게시글 등록(몇번 게시물인지 확인은 불가..)
	public void insert(BoardVO board);
	
	// 게시글 등록(PK 가져오기)
	public void insertSelectKey_bno(BoardVO board);
	
	// 게시글 상세보기(특정 게시글 정보)
	public BoardVO read(Long bno);
	
	// 게시글 삭제
	public int delete(Long bno);
	
	// 게시글 수정(수정 완료된 건수 리턴)
	public int update(BoardVO board);
}
