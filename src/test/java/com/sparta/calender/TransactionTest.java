package com.sparta.calender;

import com.sparta.calender.entity.Calender;
import com.sparta.calender.repository.CalenderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    CalenderRepository calenderRepository;

    @Test
    @Transactional
    @Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
    @DisplayName("메모 생성 성공")
    void test1() {
        Calender calender = new Calender();
        calender.setWriter("test2");
        calender.setTitle("test2");
        calender.setContent("test2");
        calender.setPassword("test2");
        calender.setDate("test2");

        em.persist(calender);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }

    @Test
    @Disabled
    @DisplayName("메모 생성 실패")
    void test2() {
        Calender calender = new Calender();
        calender.setWriter("test3");
        calender.setTitle("test3");
        calender.setContent("test3");
        calender.setPassword("test3");
        calender.setDate("test3");

        em.persist(calender);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }

    @Test
    @Disabled
//    @Transactional
//    @Rollback(value = false)
//    @DisplayName("트랜잭션 전파 테스트")
    void test3() {
//        calenderRepository.createCalender(em);
        System.out.println("테스트 test4 메서드 종료");
    }
}