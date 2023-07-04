package com.mega.vhplayer.beans.vo;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Component
@Data
public class Criteria {
	
	private int pageNum; // 현재 몇번째 페이지인가?
	private int amount; // 한 페이지에 보여줄 게시글의 개수
	
	private int limit; // 실제 보여지는 총 개수
	private int offset; // 실제 보여줄 페이지 번호
	
	// 검색 기능을 위한 field
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.limit = pageNum * amount;
		this.offset = (pageNum - 1) * amount;
	}
	
	public void setParam() {
		this.limit = pageNum * amount;
		this.offset = (pageNum - 1) * amount;
	}
	
	public String getListLink() {
		// url을 만들어 주는 클래스
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("keyword", this.keyword)
				.queryParam("type", this.type);
		
		return  builder.toUriString();
	}
	
	// 문자열 쪼개기
	public String[] getTypeArr() {
		// split(""); : 모든 문자가 분리 "ABC".split("") -> "A" "B" "C"
		// type이 null이면 아무거나 생성, null이 아니면 분할시켜 반환.
		return type == null ? new String[] {} : type.split("");
	}
}






















