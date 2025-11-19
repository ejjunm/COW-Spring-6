package com.example.demo1.repository;

//데이터 접근 계층은 소프트웨어 아키텍처에서 데이터베이스에 직접 접근하여 데이터를 CRUD하는 로직만 격리해 놓은 계층
//역할 분담과 데이터베이스 교체가 쉬운 장점
import com.example.demo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//클래스가 아닌 인터페이스로 선언: 구체적인 데이터 접근 로직을 구현 x
//실제 작동하는 코드는 스프링이 동적으로 생성
@Repository
public interface UserRepository extends JpaRepository<User, Long> { //Entity 타입과, 기본 키 타입
    //JpaRepository를 상속받음으로써, 표준화된 CRUD 메서드들을 별도의 구현 없이 사용 가능
    //save(), findById(), findAll(), deleteById()..
}

