package com.springboot.gpsapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.springboot.gpsapi.payload.Applicable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 주현
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="group_room")
public class GroupRoom 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long grId;
	
	// fk
	@JoinColumn(name="uid")
	@ManyToOne(fetch=FetchType.LAZY	)
	private User user;
	
	private String title;
	
	@Column(length = 1000)
	private String introduce;
	
	// OPEN , CLOSED
	@Enumerated(EnumType.STRING)
	private Applicable applicable;
	@Column(name="member_limit")
	private int memberLimit;
}
