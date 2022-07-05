package com.springboot.gpsapi.service;

import java.util.List;

import com.springboot.gpsapi.payload.GroupRoomDto;

public interface GroupRoomService {
	
	//Create Group Room
	public GroupRoomDto createRoom(GroupRoomDto gRoomDto, Long uid);
	//Get List All Group Room
	public List<GroupRoomDto> getAllRoomDto();
	//Get One Group Room
	public GroupRoomDto getGRoomById(Long rb_id);
	//Update Group Room
	public GroupRoomDto updateGroom(GroupRoomDto gRoomDto, Long rb_id);
	//Delete Group Room
	public void deleteGRomById(Long rb_id);

}
