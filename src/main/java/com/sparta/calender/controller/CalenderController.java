package com.sparta.calender.controller;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.service.CalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calender")
public class CalenderController {

    private final CalenderService calenderService;

    @PostMapping("/")
    public CalenderResponseDto createCalender(@RequestBody CalenderRequestDto calenderRequestDto) {
        return calenderService.createCalender(calenderRequestDto);
    }

    @GetMapping("/")
    public List<CalenderResponseDto> readCalenders() {
        return calenderService.getCalenders();
    }


    @PutMapping("/{id}")
    public Long updateCalender(@PathVariable Long id, @RequestBody CalenderRequestDto calenderRequestDto) {
        return calenderService.updateCalender(id, calenderRequestDto);
    }


    @DeleteMapping("/{id}")
    public Long deleteCalender(@PathVariable Long id) {
        return calenderService.deleteCalender(id);
    }

}
