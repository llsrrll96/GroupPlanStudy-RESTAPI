package com.springboot.gpsapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class User {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	@Column(unique = true)
	private String email;
	private String password;
	private String introduce;
	private String nickname;
}
