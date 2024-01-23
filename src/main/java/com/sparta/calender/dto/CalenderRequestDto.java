package com.sparta.calender.dto;

import lombok.Getter;

@Getter
public class CalenderRequestDto {
    private String title;
    private String content;
    private String writer;
    private String password;
    private String date;
}
