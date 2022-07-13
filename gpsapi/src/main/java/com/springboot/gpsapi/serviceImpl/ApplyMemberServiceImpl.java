package com.springboot.gpsapi.serviceImpl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gpsapi.entity.GroupApplyMember;
import com.springboot.gpsapi.entity.GroupMember;
import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.payload.APIMessage;
import com.springboot.gpsapi.payload.Applicable;
import com.springboot.gpsapi.payload.ApplyMemberDto;
import com.springboot.gpsapi.payload.GroupRole;
import com.springboot.gpsapi.repository.ApplyMemberRepository;
import com.springboot.gpsapi.repository.GroupMemberRepository;
import com.springboot.gpsapi.repository.GroupRoomRepository;
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

		try 
		{
			// 본인이 신청
			if(uid == groupRoom.getUser().getUid()) 
			{
				apiMessage.setMessage("방장 입니다.");
				return apiMessage;
			}
			
			// test clear
			if(groupRoom.getApplicable() == Applicable.CLOSED) 
			{
				apiMessage.setMessage("신청이 마감되었습니다.");
				return apiMessage;
			}
			// limit
			// test clear
			if(applyMembers.size() >= groupRoom.getMemberLimit()-1)
			{
				apiMessage.setMessage("멤버 정원이 초과되었습니다.");
				return apiMessage;
			}
			
			for(GroupApplyMember gam : applyMembers)
			{
				// already is there uid value in the apply table
				// test clear
				if(gam.getUid() == uid)
				{
					apiMessage.setMessage("이미 신청한 상태 입니다.");
					return apiMessage;
				}
			}
			
			for(GroupMember gm : groupMembers)
			{
				if(gm.getUid() == uid)
				{
					apiMessage.setMessage("이미 스터디 멤버입니다!");
					return apiMessage;
				}
			}
		}catch(NullPointerException e ) {
			e.printStackTrace();
			// group_apply table 에 값이 없을때 -> insert
		}
		groupApplyMember.setUid(uid);
		groupApplyMember.setGroupRoom(groupRoom);
		applyMemberRepository.save(groupApplyMember);
		
		apiMessage.setMessage("신청 완료");
		return apiMessage;
	}

	@Override
	@Transactional
	public APIMessage allowGroupMember(ApplyMemberDto applyMemberDto) {
		// insert groupmember and delete applymember
		
		APIMessage apiMessage = new APIMessage();
		Long grId = applyMemberDto.getGrId();
		Long uid = applyMemberDto.getUid();
		
		// 검사
		GroupRoom groupRoom=  openGroupBoardRepository.findById(grId).get();
		List<GroupMember> groupMembers = groupMemberRepository.findByGroupRoom(groupRoom);
		for(GroupMember gm : groupMembers)
		{
			if(gm.getUid() == uid)
			{
				apiMessage.setMessage("이미 스터디 멤버입니다!");
				return apiMessage;
			}
		}
		
		// insert group_member table
		GroupMember groupMember = new GroupMember();
		groupMember.setUid(uid);
		groupMember.setGroupRoom(groupRoom);
		groupMember.setRole(GroupRole.MEMBER);
		
		groupMemberRepository.save(groupMember);

		// delete in apply table
		applyMemberRepository.deleteGmIdByGroupRoomAndUid(groupRoom, uid);
		
		
		apiMessage.setMessage("승인 완료");
		return apiMessage;
	}

	@Override
	@Transactional
	public APIMessage refuseGroupMember(ApplyMemberDto applyMemberDto) {
		APIMessage apiMessage = new APIMessage();
		Long grId = applyMemberDto.getGrId();
		Long uid = applyMemberDto.getUid();
		
		GroupRoom groupRoom=  openGroupBoardRepository.findById(grId).get();
		applyMemberRepository.deleteGmIdByGroupRoomAndUid(groupRoom, uid);
		
		apiMessage.setMessage("신청 거절");
		return apiMessage;
	}

}
