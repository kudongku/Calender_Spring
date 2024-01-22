package com.sparta.calender.entity;

import com.sparta.calender.dto.CalenderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Calender {
    long id;
    String title;
    String content;
    String writer;
    int password;
    String date;

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
