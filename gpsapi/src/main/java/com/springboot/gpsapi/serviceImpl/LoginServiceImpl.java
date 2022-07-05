package com.springboot.gpsapi.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
		List<User> userList= loginRepository.findAll();
		
		return userList.stream().map(post->mapToDto(post)).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Long uid) {
		User user = loginRepository.findById(uid).get();
		
		return mapToDto(user);
	}

	@Override
	@Transactional
	public UserDto updateUser(UserDto userDto, Long uid) {
		User user = loginRepository.findById(uid).get();
		user.setEmail(userDto.getEmail());
		user.setIntroduce(userDto.getIntroduce());
		user.setNickname(userDto.getNickname());
		user.setPassword(userDto.getPassword());
		
		return mapToDto(user);
	}

	@Override
	public void deleteUserById(Long uid) {
		User user = loginRepository.findById(uid).get();
		loginRepository.delete(user);
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
