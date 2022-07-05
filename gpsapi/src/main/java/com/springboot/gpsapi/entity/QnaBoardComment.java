package com.springboot.gpsapi.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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
public class QnaBoardComment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cid;
	
	@OneToMany(mappedBy = "QnaBoard", fetch=FetchType.LAZY, cascade = CascadeType.ALL) 
	@JsonIgnoreProperties("QnaBoard")
	private QnaBoard bid;
	
	private User uid;
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="Asia/seoul")
	private Date regdate;
	
	@ManyToOne
	@Transient
	private QnaBoard qnaboard;
	

}
