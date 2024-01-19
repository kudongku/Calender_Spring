package com.sparta.calender.dto;

import lombok.Getter;

@Getter
public class CalenderRequestDto {
    String title;
    String content;
    String writer;
    int password;
    String date;
}
