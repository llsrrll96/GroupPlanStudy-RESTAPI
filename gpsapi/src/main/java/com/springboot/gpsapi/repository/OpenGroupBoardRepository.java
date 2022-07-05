package com.springboot.gpsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gpsapi.entity.GroupRoom;

public interface OpenGroupBoardRepository extends JpaRepository<GroupRoom, Long>{

	GroupRoom findByGrId(Long grId);

	List<GroupRoom> findByTitleContaining(String title);

}
