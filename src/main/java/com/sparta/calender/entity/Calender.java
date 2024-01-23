package com.sparta.calender.entity;

import com.sparta.calender.dto.CalenderRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "calender") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Calender extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "writer", nullable = false)
    private String writer;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "date", nullable = false)
    private String date;

    public Calender(CalenderRequestDto calenderRequestDto) {
        this.title = calenderRequestDto.getTitle();
        this.content = calenderRequestDto.getContent();
        this.writer = calenderRequestDto.getWriter();
        this.password = calenderRequestDto.getPassword();
        this.date = calenderRequestDto.getDate();
    }

    public void update(CalenderRequestDto calenderRequestDto) {
        this.title = calenderRequestDto.getTitle();
        this.content = calenderRequestDto.getContent();
        this.writer = calenderRequestDto.getWriter();
        this.password = calenderRequestDto.getPassword();
        this.date = calenderRequestDto.getDate();
    }

    @Override
    public String toString() {
        return "Calender{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", password=" + password +
                ", date='" + date + '\'' +
                '}';
    }
}