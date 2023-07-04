package com.mega.vhplayer.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MemberVO {
	private Long uno;
	private String user_email;
	private String user_pw;
	private String user_name;
	private String user_auth;
	private int delete_yn;
	private String regdate;
	private String updatedate;
	
	public String getUser_authString() {
		return "1".equals(user_auth) ? "Admin" : "User";
	}
	
	public String getDelete_ynString() {
		return delete_yn == 0 ? "Active" : "Inactive";
	}
}
