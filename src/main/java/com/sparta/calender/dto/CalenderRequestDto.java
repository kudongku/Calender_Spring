package com.sparta.calender.dto;

import lombok.Getter;

@Getter
public class CalenderRequestDto {
    String title;
    String content;
    String writer;
    String password;
    String date;

    @Override
    public String toString() {
        return "CalenderRequestDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", password=" + password +
                ", date='" + date + '\'' +
                '}';
    }
}
