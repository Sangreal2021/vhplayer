package com.mega.vhplayer.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	// ------------------- Admin 페이지 -------------------
	// 전체 회원 수
	public int getTotal(Criteria cri);
	
	// 회원 리스트(페이징 처리)
	public List<MemberVO> getListWithPaging(Criteria cri);
	
	// 전체 회원 리스트
	public List<MemberVO> getList();
	
	// 권한 부여 및 삭제
	public int adminUpdate(MemberVO member);
	
	// ------------------- Home 페이지 -------------------
	// 마이페이지 개인정보 조회, 수정, 삭제
	public MemberVO read(Long uno);
	public int update(MemberVO member);
	public int delete(Long uno);
	
	// 회원가입
	public void insert(MemberVO member);
	
	// 회원가입(PK 가져오기)
	public void insertSelectKey_uno(MemberVO member);
	
	
	// login 및 중복체크
	public MemberVO login(MemberVO vo) throws Exception;
	public int checkDuplicateEmail(String user_email) throws Exception;
	public int checkDuplicateName(String user_name) throws Exception;
}
