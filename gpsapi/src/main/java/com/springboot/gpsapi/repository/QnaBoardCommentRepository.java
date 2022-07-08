package com.springboot.gpsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gpsapi.entity.QnaBoard;
import com.springboot.gpsapi.entity.QnaBoardComment;

public interface QnaBoardCommentRepository extends JpaRepository<QnaBoardComment, Long> {

	List<QnaBoardComment> findByQnaBoard(QnaBoard qnaBoard);

	

}
