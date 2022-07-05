package com.springboot.gpsapi.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "group_member")
public class GroupMember 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gmId;
	
	@JoinColumn(name="gr_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private GroupRoom groupRoom;
	
	private Long uid;
	private String role;

}
