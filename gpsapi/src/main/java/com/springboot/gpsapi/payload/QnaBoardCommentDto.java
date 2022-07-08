package com.springboot.gpsapi.payload;

import java.util.Date;

import lombok.Data;

@Data
public class QnaBoardCommentDto {
	private long cid;
	
	private long uid;
	private String content;
	private Date regdate;
	
	//bid
	private QnaBoardDto qnaBoardDto;
}
