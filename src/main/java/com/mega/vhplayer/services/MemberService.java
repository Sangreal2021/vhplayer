package com.mega.vhplayer.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Service
public interface MemberService {
	
	// ------------------- Admin 페이지 -------------------
	public List<MemberVO> getList();
	public List<MemberVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public boolean modifyAdmin(MemberVO member);
	
	// ------------------- Home 페이지 -------------------
	public void register(MemberVO member) throws Exception; // 회원가입
	
	// MyPage
	public MemberVO getOne(Long uno); // 회원정보 조회
    public boolean updateMember(MemberVO member); // 회원정보 수정 
    public boolean deleteMember(Long uno); // 회원정보 삭제
	
	// ------------ Login ------------ 
    public MemberVO login(MemberVO vo) throws Exception;
    public int checkDuplicateEmail(String user_email) throws Exception;
    public int checkDuplicateName(String user_name) throws Exception;
    public void logout(HttpSession session) throws Exception;
}
