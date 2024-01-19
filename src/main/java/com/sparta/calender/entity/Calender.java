package com.sparta.calender.entity;

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
}
