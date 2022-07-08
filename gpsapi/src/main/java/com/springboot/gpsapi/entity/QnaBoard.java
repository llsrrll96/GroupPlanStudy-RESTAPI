package com.springboot.gpsapi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

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
	@JoinColumn(name = "gr_id")
	private GroupRoom groupRoom;
	
	@ManyToOne
	@JoinColumn(name = "uid")
	private User user;
	
	private String title;
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/seoul")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date regdate;
	
	
	@OneToMany(mappedBy = "qnaBoard", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval= true) 
	@JsonIgnoreProperties("qnaBoard")
	private List<QnaBoardComment> qnaboardcomments = new ArrayList<>() ;
	

}
