package com.mega.vhplayer.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AttachFileVO {

	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image; // 이미지 여부 확인용
	private boolean sound; // 음성 여부 확인용
}
