package com.springboot.gpsapi.payload;

import java.util.Date;

import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.entity.QnaBoardComment;
import com.springboot.gpsapi.entity.User;

import lombok.Data;

@Data
public class QnaBoardDto {
	
	private long bid;
	
	private GroupRoomDto grouproomDto;
	
	private UserDto userDto;
	
	private String title;
	private String content;
	private Date regdate;
	
	//QnaBoardComments
	//private List<QnaBoardComment> qnaboardcomments;

}
