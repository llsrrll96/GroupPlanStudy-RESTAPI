package com.springboot.gpsapi.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gpsapi.entity.User;
import com.springboot.gpsapi.payload.UserDto;
import com.springboot.gpsapi.repository.LoginRepository;
import com.springboot.gpsapi.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService
{
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = mapToEntity(userDto);
		User newUser=loginRepository.save(user);
		
		return mapToDto(newUser);
	}

	@Override
	public List<UserDto> getAllUserDto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Long uid) {
		// TODO Auto-generated method stub
		
	}
	
	
	// DTO -- Entity
	private User mapToEntity(UserDto userDto)
	{
		User user = mapper.map(userDto, User.class);
		return user;
	}
	
	private UserDto mapToDto(User user)
	{
		UserDto userDto = mapper.map(user, UserDto.class);
		return userDto;
	}

}
