package com.springboot.gpsapi.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class QnaBoardComment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cid;
	
//	private Long uid;
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="Asia/seoul")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date regdate;
	
	// created user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="uid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "bid") // qnaboard's 변수명
	private QnaBoard qnaBoard;
	

}
