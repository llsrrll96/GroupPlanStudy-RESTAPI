package com.springboot.gpsapi.service;

import java.util.List;

import com.springboot.gpsapi.payload.UserDto;

public interface LoginService 
{
	public UserDto createUser(UserDto userDto);
	
	public List<UserDto> getAllUserDto();
	
	public UserDto getUserById(Long uid);
	
	public UserDto updateUser(UserDto userDto, Long uid);
	
	public void deleteUserById(Long uid);
}
