package com.example.demo1.controller;

import com.example.demo1.dto.UserDto;
import com.example.demo1.repository.UserRepository;
import com.example.demo1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //클래스가 RESTful 웹 서비스의 컨트롤러임을 명시
@RequiredArgsConstructor//롬북을 이용한 생성자 주입 방식
// final로 선언된 필드에 대해 의존성을 자동으로 주입
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //새 사용자 생성
    @PostMapping
    //JSON데이터를 자바의 DTO로 변환
    public void createUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
    }

    //전체 조회
    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getAllUsers(); //JSON반환
    }

    //회원 수정
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
    }

    //회원 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) { //URL 경로에 있는 값(/{id})을 변수로 가져옴
        userService.deleteUser(id);
    }
}
