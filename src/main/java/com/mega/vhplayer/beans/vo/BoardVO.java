package com.mega.vhplayer.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String user_name;
	private String user_email;
	private String user_auth;
	private String regdate;
	private String updatedate;
}
