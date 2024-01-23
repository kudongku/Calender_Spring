package com.sparta.calender.dto;

import com.sparta.calender.entity.Calender;
import lombok.Getter;

@Getter
public class CalenderResponseDto {
    long id;
    String title;
    String content;
    String writer;
    String password;
    String date;

    public CalenderResponseDto(Calender calender) {
        this.id = calender.getId();
        this.title = calender.getTitle();
        this.content = calender.getContent();
        this.writer = calender.getWriter();
        this.password = calender.getPassword();
        this.date = calender.getDate();
    }

    public CalenderResponseDto(Long id, String title, String content, String writer, String password, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.date = date;
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
