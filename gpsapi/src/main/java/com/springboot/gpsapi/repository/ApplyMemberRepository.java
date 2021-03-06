package com.springboot.gpsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.gpsapi.entity.GroupApplyMember;
import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.entity.User;

public interface ApplyMemberRepository extends JpaRepository<GroupApplyMember, Long>
{
	List<GroupApplyMember> findByGroupRoom(GroupRoom groupRoom);

	void deleteGmIdByGroupRoomAndUser(GroupRoom groupRoom, User user);

//	@Query(name = "SELECT gm_id, gr_id FROM group_apply_member WHERE gr_id = :grId", nativeQuery = true)
//	List<GroupApplyMember> selectAllByGrId(@Param("gr_id") long grId);
	

}
