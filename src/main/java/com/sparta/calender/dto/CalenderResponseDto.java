package com.sparta.calender.dto;

import com.sparta.calender.entity.Calender;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalenderResponseDto {
    private long id;
    private String title;
    private String content;
    private String writer;
    private String date;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CalenderResponseDto(Calender calender) {
        this.id = calender.getId();
        this.title = calender.getTitle();
        this.content = calender.getContent();
        this.writer = calender.getWriter();
        this.date = calender.getDate();
        this.createdAt = calender.getCreatedAt();
        this.modifiedAt = calender.getModifiedAt();
    }
}
