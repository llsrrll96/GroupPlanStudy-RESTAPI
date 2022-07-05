package com.springboot.gpsapi.payload;

import lombok.Data;

@Data
public class UserDto 
{
	private long uid;
	private String email;
	private String password;
	private String introduce;
	private String nickname;
	
	//QnaBoard
	//private List<QnaBoard> QnaBoards;
	
	//GroupRoom
	//private List<GroupRoom> GroupRooms;
}
