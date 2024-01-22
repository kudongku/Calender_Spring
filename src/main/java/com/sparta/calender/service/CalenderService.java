package com.sparta.calender.service;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.entity.Calender;
import com.sparta.calender.repository.CalenderRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.InputMismatchException;
import java.util.List;

public class CalenderService {

    private final JdbcTemplate jdbcTemplate;

    public CalenderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public CalenderResponseDto createCalender(CalenderRequestDto calenderRequestDto) {
        // calenderRequestDto -> calenderEntity 로 변환
        Calender calender = new Calender(calenderRequestDto);

        // DB 저장
        CalenderRepository calenderRepository = new CalenderRepository(jdbcTemplate);
        Calender savedCalender = calenderRepository.save(calender);

        // Entity -> responseDto
        CalenderResponseDto calenderResponseDto = new CalenderResponseDto(savedCalender);

        return calenderResponseDto;
    }

    public List<CalenderResponseDto> getCalenders() {
        CalenderRepository calenderRepository = new CalenderRepository(jdbcTemplate);
        return calenderRepository.findAll();
    }

    public Long updateCalender(Long id, CalenderRequestDto calenderRequestDto) {
        CalenderRepository calenderRepository = new CalenderRepository(jdbcTemplate);
        // 해당 메모가 DB에 존재하는지 확인
        Calender calender = calenderRepository.findById(id);

        if (calender != null) {
            calenderRepository.update(id, calenderRequestDto);
            return id;
        } else {
            throw new InputMismatchException("선택한 일정은 존재하지 않습니다.");
        }
    }

    public Long deleteCalender(Long id) {
        CalenderRepository calenderRepository = new CalenderRepository(jdbcTemplate);
        // 해당 메모가 DB에 존재하는지 확인
        Calender calender = calenderRepository.findById(id);

        if (calender != null) {
            calenderRepository.remove(id);
            return id;
        } else {
            throw new InputMismatchException("선택한 일정은 존재하지 않습니다.");
        }
    }


}
