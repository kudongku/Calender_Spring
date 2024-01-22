package com.sparta.calender.controller;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.entity.Calender;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @PutMapping("/{id}")
    public Long updateCalender(@PathVariable Long id, @RequestBody CalenderRequestDto calenderRequestDto) {
        System.out.println(calenderRequestDto.toString());

        // 해당 id 가진 calender 존재하는가
        if(calenderMap.containsKey(id)){

            // 해당 일정 가져오기
            Calender calender = calenderMap.get(id);

            // 일정 수정하기
            calender.update(calenderRequestDto);

            System.out.println(calender.toString());

            return id;

        }else{
            throw new InputMismatchException("선택한 일정은 존재하지 않습니다.");
        }
    }


}
