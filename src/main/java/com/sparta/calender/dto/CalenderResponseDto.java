package com.sparta.calender.dto;

import com.sparta.calender.entity.Calender;
import lombok.Getter;

@Getter
public class CalenderResponseDto {
    long id;
    String title;
    String content;
    String writer;
    int password;
    String date;

    public CalenderResponseDto(Calender calender) {
        this.id = calender.getId();
        this.title = calender.getTitle();
        this.content = calender.getContent();
        this.writer = calender.getWriter();
        this.password = calender.getPassword();
        this.date = calender.getDate();
    }

    @Override
    public String toString() {
        return "CalenderResponseDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", password=" + password +
                ", date='" + date + '\'' +
                '}';
    }
}
