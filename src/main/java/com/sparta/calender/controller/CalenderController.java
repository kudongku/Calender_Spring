package com.sparta.calender.controller;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.entity.Calender;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calender")
public class CalenderController {

    private final Map<Long, Calender> calenderMap = new HashMap<>();

    @PostMapping("/")
    public CalenderResponseDto createCalender(@RequestBody CalenderRequestDto calenderRequestDto) {

        // calenderRequestDto -> calenderEntity 로 변환
        Calender calender = new Calender(calenderRequestDto);

        // calender MaxId 만들어주기
        long maxId = !calenderMap.isEmpty() ? Collections.max(calenderMap.keySet()) + 1 : 1;
        calender.setId(maxId);

        //DB 저장
        calenderMap.put(calender.getId(), calender);

        // Entity -> responseDto
        CalenderResponseDto calenderResponseDto = new CalenderResponseDto(calender);

        return calenderResponseDto;
    }

    @GetMapping("/")
    public List<CalenderResponseDto> readCalenders() {

        // map to List
        List<CalenderResponseDto> returnList = calenderMap
                .values()
                .stream()
                .map(CalenderResponseDto::new) // calenderResponseDto calender 을 인자로 받는 생성자호출
                .toList();

        return returnList;
    }


}
