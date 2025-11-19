package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //DB 테이블 선언
@Table(name = "users") //Entity와 매핑할 DB 테이블의 이름이나 세부 설정 지정
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 기본 생성자 필수
@AllArgsConstructor
public class User {
    @Id  //기본키 지정(모든 행을 구분하는 식별자)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스가 알아서 1씩 증가
    private Long id;

    @Column(nullable = false, length = 10) //빈 값 x, 길이 10 제한
    private String name;

    @Column(nullable = false, unique = true) //빈 값 x, 중복 x
    private String email;

    //캡슐화(데이터, 책임), 데이터 보호
    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
