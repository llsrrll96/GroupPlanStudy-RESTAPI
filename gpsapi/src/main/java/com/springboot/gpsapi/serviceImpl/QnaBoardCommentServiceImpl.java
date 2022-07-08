package com.springboot.gpsapi.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gpsapi.entity.QnaBoard;
import com.springboot.gpsapi.entity.QnaBoardComment;
import com.springboot.gpsapi.payload.QnaBoardCommentDto;
import com.springboot.gpsapi.payload.QnaBoardDto;
import com.springboot.gpsapi.repository.QnaBoardCommentRepository;
import com.springboot.gpsapi.repository.QnaBoardRepository;
import com.springboot.gpsapi.service.QnaBoardCommentService;

@Service
public class QnaBoardCommentServiceImpl implements QnaBoardCommentService {

	@Autowired
	private QnaBoardCommentRepository qnaBoardCommentRepository;
	@Autowired
	private QnaBoardRepository qnaboardRepository;
	@Autowired
	private ModelMapper mapper;

	// 생성
	@Override
	@Transactional
	public QnaBoardCommentDto createQnacomment(QnaBoardCommentDto qnacommentDto, long bid, long uid) {

		QnaBoardComment qnacomment = mapToEntity(qnacommentDto);

		QnaBoard qnaboard = qnaboardRepository.findById(bid).get();
		qnacomment.setQnaBoard(qnaboard);
		qnacomment.setUid(uid);

		QnaBoardComment newqnaComment = qnaBoardCommentRepository.save(qnacomment);

		return mapToDto(newqnaComment);
	}

	// 삭제
	@Override
	public void deleteQnacommentByCid(Long cid) {
		QnaBoardComment qnacomment = qnaBoardCommentRepository.findById(cid).get();
		qnaBoardCommentRepository.delete(qnacomment);
	}

	// 전체보기
	@Override
	public List<QnaBoardCommentDto> getAllQnacommentList(long bid) {
		QnaBoard qnaboard = qnaboardRepository.findById(bid).get();
		List<QnaBoardComment> qnacommentList = qnaBoardCommentRepository.findByQnaBoard(qnaboard);
		return qnacommentList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	}

	// DTO -- Entity
	private QnaBoardComment mapToEntity(QnaBoardCommentDto qnaboardcommentDto) {
		QnaBoardComment qnaboardcomment = mapper.map(qnaboardcommentDto, QnaBoardComment.class);

		QnaBoard qnaBoard = new QnaBoard();
		qnaboardcomment.setQnaBoard(qnaBoard);

		return qnaboardcomment;
	}

	private QnaBoardCommentDto mapToDto(QnaBoardComment qnaboardcomment) {
		QnaBoardCommentDto qnaboardcommentDto = mapper.map(qnaboardcomment, QnaBoardCommentDto.class);

		QnaBoardDto qnaboardDto = null;
		qnaboardDto = mapper.map(qnaboardcomment.getQnaBoard(), QnaBoardDto.class);
		qnaboardcommentDto.setQnaBoardDto(qnaboardDto);

		return qnaboardcommentDto;
	}

}
