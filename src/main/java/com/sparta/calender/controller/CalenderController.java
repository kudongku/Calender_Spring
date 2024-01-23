package com.sparta.calender.controller;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.dto.PasswordDto;
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
    public void createCalender(@RequestBody CalenderRequestDto calenderRequestDto) {
        calenderService.createCalender(calenderRequestDto);
    }

    @GetMapping("/")
    public List<CalenderResponseDto> readCalenders() {
        return calenderService.getCalenders();
    }

    @GetMapping("/{id}")
    public CalenderResponseDto showCalenderDetails(@PathVariable Long id) {
        return calenderService.showCalenderDetails(id);
    }


    @PutMapping("/{id}")
    public boolean updateCalender(@PathVariable Long id, @RequestBody CalenderRequestDto calenderRequestDto) {
        return calenderService.updateCalender(id, calenderRequestDto);
    }


    @DeleteMapping("/{id}")
    public boolean deleteCalender(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
        return calenderService.deleteCalender(id, passwordDto);
    }

}
