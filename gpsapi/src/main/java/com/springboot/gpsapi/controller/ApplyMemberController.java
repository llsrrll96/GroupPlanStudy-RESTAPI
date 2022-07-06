package com.springboot.gpsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	private APIMessage applyGroupRoom(@RequestBody ApplyMemberDto applyMemberDto)	{
		return applyMemberService.applyGroupRoom(applyMemberDto);
	}
	
	@PostMapping("/allow")
	private APIMessage allowGroupRoom(@RequestBody ApplyMemberDto applyMemberDto) {
		return applyMemberService.allowGroupRoom(applyMemberDto);
	}
	
}
