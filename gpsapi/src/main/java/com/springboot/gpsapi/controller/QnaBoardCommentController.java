package com.springboot.gpsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gpsapi.payload.APIMessage;
import com.springboot.gpsapi.payload.QnaBoardCommentDto;
import com.springboot.gpsapi.service.QnaBoardCommentService;

@RestController
@RequestMapping("/api/groom")
public class QnaBoardCommentController {

	@Autowired
	private QnaBoardCommentService qnaboardcommentservice;

	// 생성
	@PostMapping("/{grId}/qnaboard/{bid}/comment/{uid}")
	public ResponseEntity<QnaBoardCommentDto> createQnacomment(@RequestBody QnaBoardCommentDto qnacommentDto,
			@PathVariable long bid, @PathVariable long uid) {
		return new ResponseEntity<QnaBoardCommentDto>(qnaboardcommentservice.createQnacomment(qnacommentDto, bid, uid),
				HttpStatus.CREATED);
	}

	// 삭제
	@DeleteMapping("/{grId}/qnaboard/{bid}/comment/{cid}")
	public ResponseEntity<APIMessage> deleteQnacommentByCid(@PathVariable Long cid) {
		qnaboardcommentservice.deleteQnacommentByCid(cid);
		APIMessage apimessage = new APIMessage();
		apimessage.setMessage("ok");
		return new ResponseEntity<>(apimessage, HttpStatus.OK);
	}

	// 전체보기
	@GetMapping("/{grId}/qnaboard/{bid}/comment")
	private ResponseEntity<APIMessage> getAllQnacommentList(@PathVariable long grId, @PathVariable long bid) {
		APIMessage apiMessage = new APIMessage();
		apiMessage.setMessage("QnA댓글 목록");
		apiMessage.setData((List<QnaBoardCommentDto>) qnaboardcommentservice.getAllQnaCommentList(grId,bid));
		return new ResponseEntity<APIMessage>(apiMessage, HttpStatus.OK);
	}

}
