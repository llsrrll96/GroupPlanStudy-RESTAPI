package com.springboot.gpsapi.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.payload.GroupRoomDto;
import com.springboot.gpsapi.payload.UserDto;
import com.springboot.gpsapi.repository.OpenGroupBoardRepository;
import com.springboot.gpsapi.service.OpenGroupBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OpenGroupBoardServiceImpl implements OpenGroupBoardService
{
	@Autowired
	private OpenGroupBoardRepository openGroupBoardRepository;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public List<GroupRoomDto> getAllGroupRooms() {
		List<GroupRoom> groupRooms = openGroupBoardRepository.findAll();
		return groupRooms.stream().map( groupRoom -> mapToDto(groupRoom)).collect(Collectors.toList()	);
	}

	@Override
	public GroupRoomDto getGroupRoomById(Long grId) {
		GroupRoom groupRoom= openGroupBoardRepository.findByGrId(grId);
		
		return mapToDto(groupRoom);
	}

	@Override
	public List<GroupRoomDto> getGroupRoomContainingTitle(String title) {
		List<GroupRoom> groupRooms= openGroupBoardRepository.findByTitleContaining(title);
		log.info(groupRooms.get(0).getUser().getEmail());
		return groupRooms.stream().map( groupRoom -> mapToGrDto(groupRoom)		).collect(Collectors.toList());
	}
	
	
	
	private GroupRoomDto mapToDto(GroupRoom groupRoom)
	{
		return mapper.map(groupRoom, GroupRoomDto.class);
	}
	
	private GroupRoom mapToEntity(GroupRoomDto groupRoom)
	{
		return mapper.map(groupRoom, GroupRoom.class);
	}
	
	private GroupRoomDto mapToGrDto(GroupRoom groupRoom) 
	{
		GroupRoomDto openGroupDto= mapper.map(groupRoom, GroupRoomDto.class);
		
		openGroupDto.setUserDto(mapper.map(groupRoom.getUser(), UserDto.class));
		
		return openGroupDto;
	}
}
