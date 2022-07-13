package com.springboot.gpsapi.payload;

import com.springboot.gpsapi.entity.GroupRoom;

import lombok.Data;

@Data
public class GroupMemberDto2 {
	
	private Long gmId;
	private GroupRoomDto groupRoomDto;
	private Long uid;
	private GroupRole role;

}
