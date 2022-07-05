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
import com.springboot.gpsapi.payload.UserDto;
import com.springboot.gpsapi.service.LoginService;

@RestController
@RequestMapping("/api/accounts")
public class LoginController
{
	@Autowired
	private LoginService loginService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		return new ResponseEntity<>(loginService.createUser(userDto),HttpStatus.CREATED);
	}
	
	// get All User List
	@GetMapping
	public List<UserDto> getAllUser(){
		return loginService.getAllUserDto();
	}
	
	// get User by id
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name="id") long uid){
		return ResponseEntity.ok(loginService.getUserById(uid));
	}
	
	// update user by id rest api
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updatePost(@RequestBody UserDto userDto, @PathVariable(name="id") long uid){
		return new ResponseEntity<>(loginService.updateUser(userDto, uid),HttpStatus.OK);
	}
	
	// delete post by id rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<APIMessage> deletePost(@PathVariable Long id)
	{
		loginService.deleteUserById(id);
		APIMessage apimessage = new APIMessage();
		apimessage.setMessage("Delete User");
		return new ResponseEntity<>(apimessage,HttpStatus.OK);
	}
}
