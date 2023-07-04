package com.mega.vhplayer.beans.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.MemberVO;
import com.mega.vhplayer.mappers.MemberMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private SqlSession session;
	
	// ------------------- Admin 페이지 -------------------
	// 회원 수 가져오기
	public int getTotal(Criteria cri) {
		return mapper.getTotal(cri);
	}
	// 전체 회원 리스트
	public List<MemberVO> getList(){
		return mapper.getList();
	}
	// 전체 회원 리스트 가져오기(페이징 처리)
	public List<MemberVO> getList(Criteria cri){
		cri.setParam();
		return mapper.getListWithPaging(cri);
	}
	// 회원 권한 및 활성화 수정
	public boolean modifyAdmin(MemberVO member) {
		return mapper.adminUpdate(member) != 0;
	}
	
	// ------------------- Home 페이지 -------------------
	// 마이페이지 개인정보 조회, 수정, 삭제
	public MemberVO getOne(Long uno) {
		return mapper.read(uno);
	}
    public boolean modify(MemberVO member) {
        return mapper.update(member) != 0;
    }
    public boolean remove(Long uno) {
        return mapper.delete(uno) != 0;
    }
    
	// 회원가입
	public void register(MemberVO member) {
		mapper.insertSelectKey_uno(member);
	}
	
	// ------------------- Log In -------------------
	public MemberVO login(MemberVO vo) throws Exception {
		return session.getMapper(MemberMapper.class).login(vo);
	}
	public int checkDuplicateEmail(String user_email) throws Exception {
		return session.getMapper(MemberMapper.class).checkDuplicateEmail(user_email);
	}
	public int checkDuplicateName(String user_name) throws Exception {
		return session.getMapper(MemberMapper.class).checkDuplicateName(user_name);
	}
}




















