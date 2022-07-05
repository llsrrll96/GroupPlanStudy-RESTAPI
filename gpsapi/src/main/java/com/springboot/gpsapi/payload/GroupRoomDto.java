package com.springboot.gpsapi.payload;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
public class GroupRoomDto 
{
	private long grId;
	private UserDto userDto;
	private String title;
	private String introduce;
	// OPEN , CLOSED
	private Applicable applicable;
	private int memberLimit;

}
