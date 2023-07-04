package com.mega.vhplayer.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReplyVO {
	
	private Long rno;
	private Long bno;
	
	private String reply;
	private String user_name;
	private String replydate;
	private String updatedate;
}
