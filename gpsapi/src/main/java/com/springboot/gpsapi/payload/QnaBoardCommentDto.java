package com.springboot.gpsapi.payload;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QnaBoardCommentDto {
	private long cid;
	
	private long uid;
	private UserDto userDto;
	private String content;
	private Date regdate;
	
	//bid
	private QnaBoardDto qnaBoardDto;
}
