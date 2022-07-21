package com.springboot.gpsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gpsapi.entity.GroupMember;
import com.springboot.gpsapi.entity.GroupRoom;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long>
{

	List<GroupMember> findByGroupRoom(GroupRoom groupRoom);

	GroupMember findByGroupRoomAndUid(GroupRoom groupRoom, long uid);

	List<GroupMember> findByUid(Long uid);
}
