package com.springboot.gpsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gpsapi.payload.APIMessage;
import com.springboot.gpsapi.payload.QnaBoardDto;
import com.springboot.gpsapi.service.QnaBoardService;

@RestController
@RequestMapping("/api/groom")
public class QnaBoardController {

	@Autowired
	private QnaBoardService qnaboardService;

	// 생성
	@PostMapping("/{grId}/qnaboard/{uid}")
	public ResponseEntity<QnaBoardDto> createQnaboard(@RequestBody QnaBoardDto qnaboardDto, @PathVariable long grId,
			@PathVariable long uid) {
		return new ResponseEntity<QnaBoardDto>(qnaboardService.createQnaboard(qnaboardDto, grId, uid),
				HttpStatus.CREATED);
	}

	// 수정
	@PutMapping("/{grId}/qnaboard/{bid}")
	public ResponseEntity<QnaBoardDto> updateQnaboard(@RequestBody QnaBoardDto qnaboardDto, @PathVariable long bid) {
		return new ResponseEntity<QnaBoardDto>(qnaboardService.updateQnaboard(qnaboardDto, bid), HttpStatus.OK);
	}

	// 삭제
	@DeleteMapping("/{grId}/qnaboard/{bid}")
	public ResponseEntity<APIMessage> deleteQnaboardByBid(@PathVariable long bid) {
		qnaboardService.deleteQnaboardByBid(bid);
		APIMessage apimessage = new APIMessage();
		apimessage.setMessage("ok");
		return new ResponseEntity<>(apimessage, HttpStatus.OK);
	}

	// 상세보기
	@GetMapping("/{grId}/qnaboard/{bid}")
	public ResponseEntity<APIMessage> showQnaboard(@PathVariable long bid) {
		APIMessage apiMessage = new APIMessage();
		apiMessage.setMessage("QnA 보기");
		apiMessage.setData((QnaBoardDto) qnaboardService.showQnaboard(bid));
		return new ResponseEntity<APIMessage>(apiMessage, HttpStatus.OK);
	}	

	//전체보기
	@GetMapping("/{grId}/qnaboard")
	private ResponseEntity<APIMessage> getAllQnaboardList(@PathVariable(name="grId") long grId){
		APIMessage apiMessage = new APIMessage();
		apiMessage.setMessage("QnA 목록");
		apiMessage.setData((List<QnaBoardDto>) qnaboardService.getAllQnaboardList(grId));
		return new ResponseEntity<APIMessage>(apiMessage, HttpStatus.OK);
	}


}
