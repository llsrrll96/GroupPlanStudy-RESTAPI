package com.springboot.gpsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gpsapi.entity.User;

public interface LoginRepository  extends JpaRepository<User, Long>{

}
