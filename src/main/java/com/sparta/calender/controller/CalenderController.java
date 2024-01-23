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
        System.out.println("!");
        return calenderService.getCalenders();
    }

    @GetMapping("/{id}")
    public CalenderResponseDto showCalenderDetails(@PathVariable Long id) {
        System.out.println("컨트롤러 진입 성공, id : "+id);
        return calenderService.showCalenderDetails(id);
    }


    @PutMapping("/{id}")
    public boolean updateCalender(@PathVariable Long id, @RequestBody CalenderRequestDto calenderRequestDto) {
        return calenderService.updateCalender(id, calenderRequestDto);
    }


    @DeleteMapping("/{id}/{password}")
    public boolean deleteCalender(@PathVariable Long id, String password) {
        System.out.println("컨트롤러 진입 성공, password : "+password);
        return calenderService.deleteCalender(id, password);
    }

}
