package com.sparta.calender.entity;

import com.sparta.calender.dto.CalenderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
