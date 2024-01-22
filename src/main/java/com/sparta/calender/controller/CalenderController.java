package com.sparta.calender.controller;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.service.CalenderService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calender")
public class CalenderController {

    private final CalenderService calenderService;


    public CalenderController(JdbcTemplate jdbcTemplate) {
        calenderService = new CalenderService(jdbcTemplate);
    }

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
