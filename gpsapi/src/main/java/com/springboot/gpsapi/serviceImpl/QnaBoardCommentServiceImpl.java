package com.springboot.gpsapi.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gpsapi.entity.GroupApplyMember;
import com.springboot.gpsapi.entity.GroupMember;
import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.entity.QnaBoard;
import com.springboot.gpsapi.entity.QnaBoardComment;
import com.springboot.gpsapi.entity.User;
import com.springboot.gpsapi.payload.GroupMemberDto;
import com.springboot.gpsapi.payload.GroupRole;
import com.springboot.gpsapi.payload.GroupRoomDto;
import com.springboot.gpsapi.payload.QnaBoardCommentDto;
import com.springboot.gpsapi.payload.QnaBoardDto;
import com.springboot.gpsapi.payload.UserDto;
import com.springboot.gpsapi.repository.GroupMemberRepository;
import com.springboot.gpsapi.repository.GroupRoomRepository;
import com.springboot.gpsapi.repository.LoginRepository;
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
	private GroupRoomRepository groupRoomRepository;
	@Autowired
	private GroupMemberRepository groupMemberRepository;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private ModelMapper mapper;

	// 생성
	@Override
	@Transactional
	public QnaBoardCommentDto createQnacomment(QnaBoardCommentDto qnacommentDto, long bid, long uid) {

		QnaBoardComment qnacomment = mapToEntity(qnacommentDto);

		QnaBoard qnaboard = qnaboardRepository.findById(bid).get();
		qnacomment.setQnaBoard(qnaboard);
		qnacomment.setUser(loginRepository.getById(uid));
		
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
	public List<QnaBoardCommentDto> getAllQnaCommentList(long grId, long bid)
	{
		QnaBoard qnaboard = qnaboardRepository.findById(bid).get();
		
		GroupRoom groupRoom = groupRoomRepository.getById(grId);
		List<GroupMember> groupMembers = groupMemberRepository.findByGroupRoom(groupRoom);

		// get user Ids to Long Array
		Long uids[] = new Long[groupMembers.size()];
		for(int i  =  0; i < groupMembers.size(); i ++){
			uids[i] = groupMembers.get(i).getUser().getUid();
		}
		
		// get user list
		List<User> users= loginRepository.findUserInUids(uids);
		Map<Long, User> userMap = new HashMap<>();
		for(User user : users){
			userMap.put(user.getUid(), user);
		}
		
		// to QnaBoardCommentDto
		return mapToQnaCommentDto(qnaboard.getQnaboardcomments(), userMap);
	}

	private List<QnaBoardCommentDto> mapToQnaCommentDto(List<QnaBoardComment> qnaboardcomments, Map<Long, User> userMap)
	{
		List<QnaBoardCommentDto> qnaBoardCommentDtos =new ArrayList<>();
		
		for(QnaBoardComment qbc : qnaboardcomments)
		{
			QnaBoardCommentDto qnaBoardCommentDto = mapper.map(qbc, QnaBoardCommentDto.class);
			qnaBoardCommentDto.setUserDto(mapper.map(qbc.getUser(), UserDto.class));

			if(userMap.containsKey(qnaBoardCommentDto.getUserDto().getUid())) 
			{
				UserDto userDto= mapper.map(userMap.get(qnaBoardCommentDto.getUserDto().getUid()), UserDto.class);
				qnaBoardCommentDto.setUserDto(userDto);
				qnaBoardCommentDtos.add(qnaBoardCommentDto);
			}
		}
		return qnaBoardCommentDtos;
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
		UserDto userDto= mapper.map(qnaboardcomment.getUser(), UserDto.class);
		qnaboardcommentDto.setUserDto(userDto);
		return qnaboardcommentDto;
	}

}
