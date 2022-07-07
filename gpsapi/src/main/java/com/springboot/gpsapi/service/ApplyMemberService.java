package com.springboot.gpsapi.service;

import com.springboot.gpsapi.payload.APIMessage;
import com.springboot.gpsapi.payload.ApplyMemberDto;

public interface ApplyMemberService 
{
	// 그룹 방 참여 신청
	public APIMessage applyGroupRoom(ApplyMemberDto applyMemberDto);
	
	// 그룹 방 승인
	public APIMessage allowGroupMember(ApplyMemberDto applyMemberDto);
	
	// 그룹 방 거절
	public APIMessage refuseGroupMember(ApplyMemberDto applyMemberDto);
}
