package com.sparta.calender.dto;

import lombok.Getter;

@Getter
public class CalenderResponseDto {
    long id;
    String title;
    String content;
    String writer;
    int password;
    String date;
}
