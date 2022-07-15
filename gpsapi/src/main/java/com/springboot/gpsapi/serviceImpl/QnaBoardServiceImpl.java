package com.springboot.gpsapi.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gpsapi.entity.GroupRoom;
import com.springboot.gpsapi.entity.QnaBoard;
import com.springboot.gpsapi.entity.User;
import com.springboot.gpsapi.payload.GroupRoomDto;
import com.springboot.gpsapi.payload.QnaBoardDto;
import com.springboot.gpsapi.payload.UserDto;
import com.springboot.gpsapi.repository.GroupRoomRepository;
import com.springboot.gpsapi.repository.LoginRepository;
import com.springboot.gpsapi.repository.QnaBoardRepository;
import com.springboot.gpsapi.service.QnaBoardService;

@Service
public class QnaBoardServiceImpl implements QnaBoardService {
	
	@Autowired
	private QnaBoardRepository qnaboardRepository;
	@Autowired
	private GroupRoomRepository gRoomRepository;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private ModelMapper mapper;
	
	
	//생성
	@Override
	@Transactional
	public QnaBoardDto createQnaboard(QnaBoardDto qnaboardDto, Long grId, Long uid) {
		QnaBoard qnaboard = mapToEntity(qnaboardDto);
		
		User user = loginRepository.findById(uid).get();
		qnaboard.setUser(user);
		
		GroupRoom newgrouproom = gRoomRepository.findById(grId).get();
		qnaboard.setGroupRoom(newgrouproom);
		
		
		QnaBoard newqnaboard = qnaboardRepository.save(qnaboard);
		return mapToDto(newqnaboard);
		
	}
	
	//수정
	@Override
	@Transactional
	public QnaBoardDto updateQnaboard(QnaBoardDto qnaboardDto, Long bid) {
		QnaBoard qnaboard = qnaboardRepository.findById(bid).get();
		
		qnaboard.setTitle(qnaboardDto.getTitle());
		qnaboard.setContent(qnaboardDto.getContent());
				
		return mapToDto(qnaboard);
	}

	//삭제
	@Override
	public void deleteQnaboardByBid(Long bid) {
		QnaBoard qnaboard = qnaboardRepository.findById(bid).get();
		qnaboardRepository.delete(qnaboard);
		
	}
	
	//상세보기
	@Override
	public QnaBoardDto showQnaboard(Long bid) {
		QnaBoard qnaboard = qnaboardRepository.findById(bid).get();
		return mapToDto(qnaboard);
	}
	
	
	//전체보기
	@Override
	public List<QnaBoardDto> getAllQnaboardList(long grId) {
		GroupRoom groupRoom = gRoomRepository.findById(grId).get();
		List<QnaBoard> qnaboardList = qnaboardRepository.findByGroupRoom(groupRoom);
		return qnaboardList.stream().map(post->mapToDto(post)).collect(Collectors.toList());
	}
	
	
	
	// DTO -- Entity
	private QnaBoard mapToEntity(QnaBoardDto qnaboardDto){
		QnaBoard qnaboard = mapper.map(qnaboardDto, QnaBoard.class);
		
		GroupRoom gRoom= new GroupRoom();
		qnaboard.setGroupRoom(gRoom);
		
		User user = new User();
		qnaboard.setUser(user);
		
		return qnaboard;
	}
	
	private QnaBoardDto mapToDto(QnaBoard qnaboard){
		QnaBoardDto qnaboardDto = mapper.map(qnaboard, QnaBoardDto.class);
		
		GroupRoomDto gRoomDto= null;
		gRoomDto = mapper.map(qnaboard.getGroupRoom(), GroupRoomDto.class);
		qnaboardDto.setGrouproomDto(gRoomDto);
		
		UserDto userDto = null;
		userDto = mapper.map(qnaboard.getUser() , UserDto.class);
		qnaboardDto.setUserDto(userDto);
		
		return qnaboardDto;
	}
	
}
