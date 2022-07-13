package com.springboot.gpsapi.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gpsapi.entity.GroupApplyMember;
import com.springboot.gpsapi.entity.GroupMember;
import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.entity.User;
import com.springboot.gpsapi.payload.GroupMemberDto;
import com.springboot.gpsapi.payload.GroupMemberDto2;
import com.springboot.gpsapi.payload.GroupRole;
import com.springboot.gpsapi.payload.GroupRoomDto;
import com.springboot.gpsapi.payload.UserDto;
import com.springboot.gpsapi.repository.ApplyMemberRepository;
import com.springboot.gpsapi.repository.GroupMemberRepository;
import com.springboot.gpsapi.repository.GroupRoomRepository;
import com.springboot.gpsapi.repository.LoginRepository;
import com.springboot.gpsapi.service.GroupRoomMemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupRoomMemberServiceImpl implements GroupRoomMemberService
{
	@Autowired
	private ApplyMemberRepository applyMemberRepository;
	@Autowired
	private GroupMemberRepository groupMemberRepository;
	@Autowired
	private GroupRoomRepository groupRoomRepository;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private ModelMapper mapper;

	private UserDto entityToDto(User user)
	{
		UserDto userDto = new UserDto();
		userDto.setUid(user.getUid());
		userDto.setEmail(user.getEmail());
		userDto.setIntroduce(user.getIntroduce());
		userDto.setNickname(user.getNickname());
	
		return userDto;
	}
	private GroupMemberDto entityToGroupMemberDto(User user,GroupRoom groupRoom)
	{
		GroupMemberDto groupMemberDto = new GroupMemberDto();
		groupMemberDto.setGrId(groupRoom.getGrId());
		groupMemberDto.setUid(user.getUid());
		groupMemberDto.setName(user.getNickname());
		groupMemberDto.setIntro(user.getIntroduce());
		
		if (groupRoom.getUser().getUid() == user.getUid()) groupMemberDto.setRole(GroupRole.LEADER);
		else groupMemberDto.setRole(GroupRole.MEMBER);
		
		return groupMemberDto;
		
	}
	
	@Override
	public List<UserDto> getApplyMembers(long grId) 
	{
		// 멤버 리스트 (닉네임)
		GroupRoom groupRoom = groupRoomRepository.getById(grId);
		List<GroupApplyMember> groupApplyMembers = applyMemberRepository.findByGroupRoom(groupRoom);
		
		// get user Ids to Long Array
		Long uids[] = new Long[groupApplyMembers.size()];
		for(int i  =  0; i < groupApplyMembers.size(); i ++){
			uids[i] = groupApplyMembers.get(i).getUid();
		}
		
		// get user list
		List<User> users= loginRepository.findUserInUids(uids);
		
		return users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
	}

	@Override
	public List<GroupMemberDto> getGroupMembers(long grId) 
	{
		GroupRoom groupRoom = groupRoomRepository.getById(grId);
		List<GroupMember> groupMembers = groupMemberRepository.findByGroupRoom(groupRoom);

		// get user Ids to Long Array
		Long uids[] = new Long[groupMembers.size()];
		for(int i  =  0; i < groupMembers.size(); i ++){
			uids[i] = groupMembers.get(i).getUid();
		}
		
		// get user list
		List<User> users= loginRepository.findUserInUids(uids);
		
		return users.stream().map(user -> entityToGroupMemberDto(user, groupRoom)).collect(Collectors.toList());
	}
	
	@Override
	public GroupMemberDto getGroupMemberByUid(long grId, long uid) 
	{
		GroupRoom groupRoom = groupRoomRepository.getById(grId);
		GroupMember groupMember = groupMemberRepository.findByGroupRoomAndUid(groupRoom, uid);
		log.info("groupMember: " + groupMember.getUid());
		User user = loginRepository.findById(uid).get();
		
		return entityToGroupMemberDto(user, groupRoom);
	}
	
	
	@Override
	public List<GroupMemberDto2> getMyGroup(long uid) {
		List<GroupMember> groupMember = groupMemberRepository.findByUid(uid);
		
		return groupMember.stream().map(post->mapToDto(post)).collect(Collectors.toList());
	}
	
	@Override
	public List<GroupMemberDto2> getAllMemberDto() {
		List<GroupMember> groupMember = groupMemberRepository.findAll();
		
		return groupMember.stream().map(post->mapToDto(post)).collect(Collectors.toList());
	}
	
	//Entity -- Dto
	private GroupMemberDto2 mapToDto(GroupMember groupMember)
	{
		GroupMemberDto2 groupMemberDto2 = mapper.map(groupMember, GroupMemberDto2.class);
		groupMemberDto2.setGroupRoomDto(mapper.map(groupMember.getGroupRoom(), GroupRoomDto.class));
		
		return groupMemberDto2;
	}

}
