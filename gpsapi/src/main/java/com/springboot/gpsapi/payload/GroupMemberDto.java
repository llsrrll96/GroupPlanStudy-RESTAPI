package com.springboot.gpsapi.payload;

import lombok.Data;

@Data
public class GroupMemberDto 
{
	private long grId;
	private long uid;
	private String name;
	private String intro;
	private GroupRole role;
}
