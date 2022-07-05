package com.springboot.gpsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gpsapi.payload.GroupRoomDto;
import com.springboot.gpsapi.service.OpenGroupBoardService;

@RestController
@RequestMapping("/api/opengroups")
public class OpenGroupBoardController 
{
	@Autowired
	private OpenGroupBoardService openGroupBoardService;
	
	// get by id
	@GetMapping("/{id}")
	public ResponseEntity<GroupRoomDto> getGroupRoomById(@PathVariable(name = "id") Long grId)
	{
		GroupRoomDto  groupRoomDto= openGroupBoardService.getGroupRoomById(grId);
		return new ResponseEntity<>(groupRoomDto, HttpStatus.CREATED);
	}

//	// get  list
//	@GetMapping
//	public List<GroupRoomDto> getAllGroupRooms(){
//		return openGroupBoardService.getAllGroupRooms();
//	}
	
	// get seached list
	@GetMapping
	public List<GroupRoomDto> getGroupRoomByTitle(@RequestParam(defaultValue="",required=false) String title){
		return openGroupBoardService.getGroupRoomContainingTitle(title);
	}

}
