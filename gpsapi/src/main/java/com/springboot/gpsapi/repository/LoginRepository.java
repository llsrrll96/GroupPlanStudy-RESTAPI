package com.springboot.gpsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.gpsapi.entity.User;

public interface LoginRepository  extends JpaRepository<User, Long>{

	@Query(value = "select * from user u where u.uid in :uids",
			nativeQuery = true) // 동적으로 쿼리 날릴때 true
	List<User> findUserInUids(@Param("uids") Long[] uids);

}
