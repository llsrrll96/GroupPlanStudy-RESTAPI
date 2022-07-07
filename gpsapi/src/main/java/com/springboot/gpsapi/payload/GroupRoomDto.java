package com.springboot.gpsapi.payload;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
public class GroupRoomDto implements Comparable<GroupRoomDto>
{
	private long grId;
	private UserDto userDto;
	private String title;
	private String introduce;
	// OPEN , CLOSED
	private Applicable applicable;
	private int memberLimit;
	
	
	@Override
	public int compareTo(GroupRoomDto dto) {
		if(dto.grId < grId) {
			return 1;
		}else return -1;
	}

}
