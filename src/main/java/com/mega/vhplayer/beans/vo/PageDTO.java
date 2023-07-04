package com.mega.vhplayer.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class PageDTO {
	// 현재 디스플레이된 페이지에서 처음과 끝 
	private int startPage;
	private int endPage;
	// 실제 총 게시글 중 젤 마지막 번호
	private int realEnd;
	// 첫페이지에서는 이전이 없고, 마지막페이지는 다음이 없어야 함
	private boolean prev, next;
	// 전체 게시글 수
	private int total;
	// 한 페이지에 몇개 들어있는지 정하기 위해
	private Criteria cri;
	
	// 생성자
	public PageDTO() {}
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		// 게시글이 1건 -> start 1	end 1
		// endPage가 1 ... 10까지는 startPage가 1,
		// endPage가 11이 넘어가면 startPage는 11 -> endPage-9 = 2(페이지)
		endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		
		// 시작은 끝페이지 - 9 => 총 10개의 페이지가 위치하겠다..
		// 10개 까지는 페이지 넘버는 1이어야 함
		startPage = endPage - 9;
		
		// 15.1페이지이면 16페이지가 필요!
		realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
//		log.info("--------- endPage : " + endPage);
//		log.info("--------- realEnd : " + realEnd);
		
		// 잘못된 상황
		if(realEnd < this.endPage) {
			// 맥시멈을 realEnd로 제한걸기 그리고 실제로 0이면 보여줄 페이지는 1임
			endPage = (realEnd == 0) ? 1 : realEnd;
		}
		
		prev = (startPage > 1); // true or false
		next = (endPage < realEnd); // true or false
	}
}




















