package com.springboot.gpsapi.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class QnaBoard {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bid;
	
	@OneToMany(mappedBy = "GroupRoom", fetch=FetchType.LAZY) 
	@JsonIgnoreProperties("GroupRoom")
	@Transient
	private GroupRoom gr_id;
	
	@OneToMany(mappedBy = "User", fetch=FetchType.LAZY) 
	@JsonIgnoreProperties("User")
	@Transient
	private User uid;
	
	private String title;
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="Asia/seoul")
	private Date regdate;
	

}
