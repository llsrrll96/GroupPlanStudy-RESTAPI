package com.springboot.gpsapi.service;

import java.util.List;

import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.payload.QnaBoardDto;

public interface QnaBoardService {
	
	//생성
	public QnaBoardDto createQnaboard(QnaBoardDto qnaboardDto, Long grId, Long uid);
	
	//수정
	public QnaBoardDto updateQnaboard(QnaBoardDto qnaboardDto, Long bid);

	//삭제
	public void deleteQnaboardByBid(Long bid);
	
	//보기
	public QnaBoardDto showQnaboard(Long bid);
	
//	//전체보기
//	public List<QnaBoardDto> getAllQnaboardList();
	
	//전체보기
	public List<QnaBoardDto> getAllQnaboardList(long grId);

}
