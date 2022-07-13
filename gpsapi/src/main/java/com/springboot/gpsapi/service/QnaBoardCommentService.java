package com.springboot.gpsapi.service;

import java.util.List;

import com.springboot.gpsapi.payload.QnaBoardCommentDto;

public interface QnaBoardCommentService {

	// 생성
	public QnaBoardCommentDto createQnacomment(QnaBoardCommentDto qnacommentDto, long bid, long uid);

	// 삭제
	public void deleteQnacommentByCid(Long cid);

	// 전체보기
	public List<QnaBoardCommentDto> getAllQnaCommentList(long grId, long bid);

}
