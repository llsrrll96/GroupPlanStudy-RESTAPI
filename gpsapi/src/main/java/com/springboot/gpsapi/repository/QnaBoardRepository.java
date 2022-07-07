package com.springboot.gpsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.entity.QnaBoard;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {

	List<QnaBoard> findByGroupRoom(GroupRoom groupRoom);

	

}
