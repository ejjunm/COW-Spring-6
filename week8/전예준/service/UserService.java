package com.example.demo1.service;

import com.example.demo1.dto.UserDto;
import com.example.demo1.entity.User;
import com.example.demo1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//트랜잭션 처리를 위해 추가
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//DTO와 Entity를 분리하여 결합도를 낮추는 패턴. Controller와 Repository 사이에서 데이터 변환 필수
@Service
@RequiredArgsConstructor //생성자 자동 생성(의존성 주입)
//데이터베이스의 상태를 변화시키는 작업의 단위
//ACID(원자성, 일관성, 격리성, 지속성) 성질 보장
//메서드 실행 도중 예외가 발생하면 롤백하여 데이터 무결성 유지
@Transactional(readOnly = true) //읽기 모드 (성능 최적화)
public class UserService {

    private final UserRepository userRepository;

    //사용자 추가
    @Transactional
    public void addUser(UserDto userDto) {
        // DTO -> Entity 변환
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();

        //저장 (JPA가 알아서 전송)
        userRepository.save(user);
    }

    //전체 조회
    public List<UserDto> getAllUsers() {
        //DB에서 Entity List를 가져 옴
        List<User> users = userRepository.findAll();

        //Entity List -> DTO List 변환
        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    //수정
    @Transactional
    public void updateUser(Long id, UserDto userDto) {
        //수정할 데이터를 찾음(없으면 에러 발생)
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));

        //데이터 변경
        user.update(userDto.getName(), userDto.getEmail());
    }

    //삭제
    @Transactional
    public void deleteUser(Long id) {
        //삭제할 데이터 찾음
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));

        //데이터 삭제
        userRepository.delete(user);
    }
}