package com.springboot.gpsapi.serviceImpl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gpsapi.entity.GroupApplyMember;
import com.springboot.gpsapi.entity.GroupMember;
import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.entity.User;
import com.springboot.gpsapi.payload.APIMessage;
import com.springboot.gpsapi.payload.Applicable;
import com.springboot.gpsapi.payload.ApplyMemberDto;
import com.springboot.gpsapi.payload.GroupRole;
import com.springboot.gpsapi.repository.ApplyMemberRepository;
import com.springboot.gpsapi.repository.GroupMemberRepository;
import com.springboot.gpsapi.repository.GroupRoomRepository;
import com.springboot.gpsapi.repository.LoginRepository;
import com.springboot.gpsapi.repository.OpenGroupBoardRepository;
import com.springboot.gpsapi.service.ApplyMemberService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplyMemberServiceImpl implements ApplyMemberService
{
	@Autowired
	private ApplyMemberRepository applyMemberRepository;
	
	@Autowired
	private GroupMemberRepository groupMemberRepository;
	
	@Autowired
	private OpenGroupBoardRepository openGroupBoardRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
//	@Transactional
	public APIMessage applyGroupRoom(ApplyMemberDto applyMemberDto) 
	{
		APIMessage apiMessage = new APIMessage();
		GroupApplyMember groupApplyMember = new GroupApplyMember();
		Long grId = applyMemberDto.getGrId();
		Long uid = applyMemberDto.getUid();

		// constraints : 
		// limit
		// already is there uid value in the apply table
		// applicable
		GroupRoom groupRoom=  openGroupBoardRepository.findById(grId).get();
		List<GroupApplyMember> applyMembers = applyMemberRepository.findByGroupRoom(groupRoom);
		List<GroupMember> groupMembers = groupMemberRepository.findByGroupRoom(groupRoom);
		User user = loginRepository.findById(uid).get();

		try 
		{
			// ????????? ??????
			if(uid == groupRoom.getUser().getUid()) 
			{
				apiMessage.setMessage("?????? ?????????.");
				return apiMessage;
			}
			
			// test clear
			if(groupRoom.getApplicable() == Applicable.CLOSED) 
			{
				apiMessage.setMessage("????????? ?????????????????????.");
				return apiMessage;
			}
			// limit
			// test clear
			if(applyMembers.size() >= groupRoom.getMemberLimit()-1)
			{
				apiMessage.setMessage("?????? ????????? ?????????????????????.");
				return apiMessage;
			}
			
			for(GroupApplyMember gam : applyMembers)
			{
				// already is there uid value in the apply table
				// test clear
				if(gam.getUser().getUid() == uid)
				{
					apiMessage.setMessage("?????? ????????? ?????? ?????????.");
					return apiMessage;
				}
			}
			
			for(GroupMember gm : groupMembers)
			{
				if(gm.getUser().getUid() == uid)
				{
					apiMessage.setMessage("?????? ????????? ???????????????!");
					return apiMessage;
				}
			}
		}catch(NullPointerException e ) {
			e.printStackTrace();
			// group_apply table ??? ?????? ????????? -> insert
		}
		groupApplyMember.setUser(user);
		groupApplyMember.setGroupRoom(groupRoom);
		applyMemberRepository.save(groupApplyMember);
		
		apiMessage.setMessage("?????? ??????");
		return apiMessage;
	}

	@Override
	@Transactional
	public APIMessage allowGroupMember(ApplyMemberDto applyMemberDto) {
		// insert groupmember and delete applymember
		
		APIMessage apiMessage = new APIMessage();
		Long grId = applyMemberDto.getGrId();
		Long uid = applyMemberDto.getUid();
		
		// ??????
		GroupRoom groupRoom=  openGroupBoardRepository.findById(grId).get();
		List<GroupMember> groupMembers = groupMemberRepository.findByGroupRoom(groupRoom);
		for(GroupMember gm : groupMembers)
		{
			if(gm.getUser().getUid() == uid)
			{
				apiMessage.setMessage("?????? ????????? ???????????????!");
				return apiMessage;
			}
		}
		
		// insert group_member table
		GroupMember groupMember = new GroupMember();
		groupMember.setUser(loginRepository.findById(uid).get());
		groupMember.setGroupRoom(groupRoom);
		groupMember.setRole(GroupRole.MEMBER);
		
		groupMemberRepository.save(groupMember);

		// delete in apply table
		applyMemberRepository.deleteGmIdByGroupRoomAndUser(groupRoom, groupMember.getUser());
		
		
		apiMessage.setMessage("?????? ??????");
		return apiMessage;
	}

	@Override
	@Transactional
	public APIMessage refuseGroupMember(ApplyMemberDto applyMemberDto) {
		APIMessage apiMessage = new APIMessage();
		Long grId = applyMemberDto.getGrId();
		Long uid = applyMemberDto.getUid();
		
		GroupRoom groupRoom=  openGroupBoardRepository.findById(grId).get();
		applyMemberRepository.deleteGmIdByGroupRoomAndUser(groupRoom, loginRepository.findById(uid).get());
		
		apiMessage.setMessage("?????? ??????");
		return apiMessage;
	}

}
