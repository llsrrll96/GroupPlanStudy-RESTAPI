package com.springboot.gpsapi.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class User {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private String introduce;
	private String nickname;
	
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL, orphanRemoval = true)
	private List<QnaBoard> qnaBoards = new ArrayList<>();
	
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL, orphanRemoval = true)
	private List<GroupRoom> groupRooms = new ArrayList<>();
	
}
