package com.springboot.gpsapi.service;

import java.util.List;

import com.springboot.gpsapi.payload.GroupRoomDto;

public interface OpenGroupBoardService 
{
	// get All Group Room List
	public  List<GroupRoomDto> getAllGroupRooms();
	
	// get Group By Id
	public GroupRoomDto getGroupRoomById(Long grId);
	
	// search Group Room
	public List<GroupRoomDto> getGroupRoomContainingTitle(String title);

}
