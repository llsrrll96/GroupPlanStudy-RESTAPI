package com.springboot.gpsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gpsapi.entity.GroupMember;
import com.springboot.gpsapi.payload.APIMessage;
import com.springboot.gpsapi.payload.GroupMemberDto;
import com.springboot.gpsapi.payload.GroupMemberDto2;
import com.springboot.gpsapi.payload.UserDto;
import com.springboot.gpsapi.service.GroupRoomMemberService;

@RestController
@RequestMapping("/api/groupmember")
public class GroupRoomMemberController 
{
	@Autowired
	private GroupRoomMemberService groupRoomMemberService;
	
	@GetMapping("/applyMembers/{grid}")
	private ResponseEntity<APIMessage> getApplyMembers(@PathVariable(name = "grid") long grId)
	{
		APIMessage apiMessage = new APIMessage();
		apiMessage.setMessage("신청 멤버 목록");
		apiMessage.setData((List<UserDto>)groupRoomMemberService.getApplyMembers(grId));
		return new ResponseEntity<APIMessage>(apiMessage, HttpStatus.OK);
	}
	
	@GetMapping("/groupMembers/{grid}")
	private ResponseEntity<APIMessage> getGroupMembers(@PathVariable(name = "grid") long grId)
	{
		APIMessage apiMessage = new APIMessage();
		apiMessage.setMessage("멤버 목록");
		apiMessage.setData((List<GroupMemberDto>)groupRoomMemberService.getGroupMembers(grId));
		return new ResponseEntity<APIMessage>(apiMessage, HttpStatus.OK);
	}
	
	@GetMapping("/groupMembers/{grid}/{uid}")
	private ResponseEntity<APIMessage> getGroupMemberByUid(@PathVariable(name="grid") long grId, @PathVariable long uid)
	{
		APIMessage apiMessage= new APIMessage();
		apiMessage.setMessage("멤버");
		apiMessage.setData((GroupMemberDto) groupRoomMemberService.getGroupMemberByUid(grId, uid));
		return new ResponseEntity<APIMessage>(apiMessage, HttpStatus.OK);
	}
	
	//내가 들어간 그룹
	@GetMapping("/{uid}")
	private ResponseEntity<APIMessage> getMyGroup(@PathVariable(name = "uid")long uid){
		APIMessage apiMessage = new APIMessage();
		apiMessage.setData(groupRoomMemberService.getMyGroup(uid));
		apiMessage.setMessage("내 그룹");
		return new ResponseEntity<APIMessage>(apiMessage, HttpStatus.OK);
	}
	
	//전체 그룹 맴버
	@GetMapping
	public List<GroupMemberDto2> getAllMember(){
		return groupRoomMemberService.getAllMemberDto();
	}
	
}
