package com.springboot.gpsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gpsapi.payload.APIMessage;
import com.springboot.gpsapi.payload.GroupRoomDto;
import com.springboot.gpsapi.service.GroupRoomService;

@RestController
@RequestMapping("/api/groom")
public class GroupRoomController {
	@Autowired
	private GroupRoomService grService;
	
	//create room
	@PostMapping("/{id}")
	public ResponseEntity<GroupRoomDto> createGRoom(@RequestBody GroupRoomDto gRoomDto, @PathVariable(name="id") long uid){
		return new ResponseEntity<>(grService.createRoom(gRoomDto,uid),HttpStatus.CREATED);
	}
	
	//get All group room
	@GetMapping
	public List<GroupRoomDto> getAllGRoom(){
		return grService.getAllRoomDto();
	}
	
	//get Group room by id
	@GetMapping("/{id}")
	public ResponseEntity<GroupRoomDto> getGRoomById(@PathVariable(name="id") long gr_id){
		return ResponseEntity.ok(grService.getGRoomById(gr_id));
	}
	
	//update Group room by id rest api
	@PutMapping("/{id}")
	public ResponseEntity<GroupRoomDto> updateGRoom(@RequestBody GroupRoomDto gRoomDto, @PathVariable(name="id") long gr_id){
		return new ResponseEntity<>(grService.updateGroom(gRoomDto, gr_id),HttpStatus.OK);
	}
	
	//delete Group room by id rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<APIMessage> deleteGRoom(@PathVariable(name="id") long gr_id){
		grService.deleteGRomById(gr_id);
		APIMessage apimessage = new APIMessage();
		apimessage.setMessage("Delete Group Room");
		return new ResponseEntity<>(apimessage,HttpStatus.OK);
	}
	
	
}
