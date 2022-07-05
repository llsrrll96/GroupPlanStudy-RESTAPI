package com.springboot.gpsapi.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class QnaBoard {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bid;
	
	@ManyToOne
	@JsonIgnoreProperties("GroupRoom")
	private GroupRoom gr_id;
	
	@OneToMany(mappedBy = "User", fetch=FetchType.LAZY, cascade = CascadeType.ALL) 
	@JsonIgnoreProperties("User")
	private User uid;
	
	private String title;
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="Asia/seoul")
	private Date regdate;
	
	
	@OneToMany(mappedBy = "QnaBoard", fetch=FetchType.LAZY) 
	@JsonIgnoreProperties("QnaBoard")
	private List<QnaBoardComment> qnaboardcomment;
	

}
