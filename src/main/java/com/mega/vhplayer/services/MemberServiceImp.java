package com.mega.vhplayer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.vhplayer.beans.dao.MemberDAO;
import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberServiceImp implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	// ------------------- Admin 페이지 -------------------
	@Override
	public List<MemberVO> getList() {
		return dao.getList();
	}
	@Override
	public List<MemberVO> getList(Criteria cri) {
		return dao.getList(cri);
	}
	@Override
	public int getTotal(Criteria cri) {
		return dao.getTotal(cri);
	}
	@Override
	public boolean modifyAdmin(MemberVO member) {
		return dao.modifyAdmin(member);
	}
	
	// ------------------- Home 페이지 -------------------
	@Override
	public void register(MemberVO vo) throws Exception { // 회원가입
		if(dao.checkDuplicateEmail(vo.getUser_email()) > 0) {
			throw new Exception("Duplicate Email!");
		}
		if(dao.checkDuplicateName(vo.getUser_name()) > 0) {
			throw new Exception("Duplicate NickName");
		}
		dao.register(vo);
	}
	
	// MyPage 조회, 수정, 삭제
	@Override
	public MemberVO getOne(Long uno) { // 조회
		return dao.getOne(uno);
	}
	@Override
	public boolean updateMember(MemberVO member) { // 수정
		return dao.modify(member);
	}
	@Override
	public boolean deleteMember(Long uno) { // 삭제
		return dao.remove(uno);
	}
	
	// ------------ Login ------------
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	@Override
	public int checkDuplicateEmail(String user_email) throws Exception {
		return dao.checkDuplicateEmail(user_email);
	}
	@Override
	public int checkDuplicateName(String user_name) throws Exception {
		return dao.checkDuplicateName(user_name);
	}
	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();
	}



	
}
