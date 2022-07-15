package com.springboot.gpsapi.service;

import java.util.List;

import com.springboot.gpsapi.entity.GroupApplyMember;
import com.springboot.gpsapi.entity.GroupMember;
import com.springboot.gpsapi.payload.GroupMemberDto;
import com.springboot.gpsapi.payload.GroupMemberDto2;
import com.springboot.gpsapi.payload.UserDto;

public interface GroupRoomMemberService 
{
	// 신청 멤버 목록 보기
	List<UserDto> getApplyMembers(long grId);
	
	// 그룹방의 참여 멤버 목록 보기
	List<GroupMemberDto> getGroupMembers(long grId);
	
	GroupMemberDto getGroupMemberByUid(long grId, long uid);
	
	List<GroupMemberDto2> getMyGroup(long uid);
	
	List<GroupMemberDto2> getAllMemberDto();
}
