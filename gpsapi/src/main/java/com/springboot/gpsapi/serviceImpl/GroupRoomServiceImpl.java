package com.springboot.gpsapi.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.entity.User;
import com.springboot.gpsapi.payload.Applicable;
import com.springboot.gpsapi.payload.GroupRoomDto;
import com.springboot.gpsapi.payload.UserDto;
import com.springboot.gpsapi.repository.GroupRoomRepository;
import com.springboot.gpsapi.repository.LoginRepository;
import com.springboot.gpsapi.service.GroupRoomService;

@Service
public class GroupRoomServiceImpl implements GroupRoomService{
	
	@Autowired
	private GroupRoomRepository gRoomRepository;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public GroupRoomDto createRoom(GroupRoomDto gRoomDto, Long uid) {
		GroupRoom gRoom = mapToEntity(gRoomDto);
		User user = loginRepository.findById(uid).get();
		gRoom.setUser(user);
		
		GroupRoom newGRoom = gRoomRepository.save(gRoom);
		
		return mapToDto(newGRoom);
	}

	@Override
	public List<GroupRoomDto> getAllRoomDto() {
		List<GroupRoom> groomList = gRoomRepository.findAll();
		
		return groomList.stream().map(post->mapToDto(post)).collect(Collectors.toList());
	}

	@Override
	public GroupRoomDto getGRoomById(Long rb_id) {
		GroupRoom gRoom = gRoomRepository.findById(rb_id).get();
		
		return mapToDto(gRoom);
	}

	@Override
	@Transactional
	public GroupRoomDto updateGroom(GroupRoomDto gRoomDto, Long rb_id) {
		GroupRoom gRoom = gRoomRepository.findById(rb_id).get();
		
		System.out.println(gRoom.getUser());
		System.out.println(gRoom.getGrId());
		
		gRoom.setApplicable(gRoomDto.getApplicable());
		gRoom.setIntroduce(gRoomDto.getIntroduce());
		gRoom.setMemberLimit(gRoomDto.getMemberLimit());
		gRoom.setTitle(gRoomDto.getTitle());
		
		return mapToDto(gRoom);
	}

	@Override
	public void deleteGRomById(Long rb_id) {
		GroupRoom gRoom = gRoomRepository.findById(rb_id).get();
		gRoomRepository.delete(gRoom);
	}
	
	//DTO -- Entity
	private GroupRoom mapToEntity(GroupRoomDto gRoomDto) {
		GroupRoom gRoom = mapper.map(gRoomDto, GroupRoom.class);
		return gRoom;
	}
	
	private GroupRoomDto mapToDto(GroupRoom gRoom) {
		GroupRoomDto gRoomDto = mapper.map(gRoom, GroupRoomDto.class);
		gRoomDto.setUserDto(mapper.map(gRoom.getUser(), UserDto.class));
		return gRoomDto;
	}

}
