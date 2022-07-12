package com.springboot.gpsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gpsapi.payload.APIMessage;
import com.springboot.gpsapi.payload.ApplyMemberDto;
import com.springboot.gpsapi.service.ApplyMemberService;

@RestController
@RequestMapping("/api/applymember")
public class ApplyMemberController 
{
	@Autowired
	private ApplyMemberService applyMemberService ;
	
	// apply
	@PostMapping
	private  ResponseEntity<APIMessage> applyGroupRoom(@RequestBody ApplyMemberDto applyMemberDto)	{
		APIMessage apiMessage = applyMemberService.applyGroupRoom(applyMemberDto);
		return new ResponseEntity<>(apiMessage,HttpStatus.OK);
	}
	
	@PostMapping("/allow")
	private ResponseEntity<APIMessage> allowGroupMember(@RequestBody ApplyMemberDto applyMemberDto) {
		APIMessage apiMessage= applyMemberService.allowGroupMember(applyMemberDto);
		return new ResponseEntity<>(apiMessage,HttpStatus.OK); 
	}
	
	@PostMapping("/refuse")
	private ResponseEntity<APIMessage> refuseGroupMember(@RequestBody ApplyMemberDto applyMemberDto){
		APIMessage apiMessage= applyMemberService.refuseGroupMember(applyMemberDto);
		return new ResponseEntity<>(apiMessage,HttpStatus.OK); 
	}
	
}
