package com.mega.vhplayer.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.ReplyVO;

@Mapper
public interface ReplyMapper {
	
	// 댓글 등록
	public int insert(ReplyVO reply);
	
	// 댓글 1개 조회
	public ReplyVO read(Long rno);
	
	// 댓글 삭제
	public int delete(Long rno);
	
	// 댓글 수정
	public int update(ReplyVO reply);
	
	// 댓글 목록
	// 댓글은 목록을 유지하면서 따로 볼수 있어야 하므로 매개변수 2개 필요
	// 아래도 현재는 개선되어 가능함!
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	// 특정 rno, bno가 일치하는 글 가져오기(Test)
	public ReplyVO getTest(Long rno, Long bno);
}
