package com.sparta.calender.service;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.entity.Calender;
import com.sparta.calender.repository.CalenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    public CalenderResponseDto createCalender(CalenderRequestDto calenderRequestDto) {
        // calenderRequestDto -> calenderEntity 로 변환
        Calender calender = new Calender(calenderRequestDto);

        // DB 저장
        Calender savedCalender = calenderRepository.save(calender);

        // Entity -> responseDto
        return new CalenderResponseDto(savedCalender);
    }

    public List<CalenderResponseDto> getCalenders() {
        return calenderRepository
                .findAll()
                .stream()
                .map(CalenderResponseDto::new)
                .toList();
    }

    @Transactional
    public Long updateCalender(Long id, CalenderRequestDto calenderRequestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Calender calender = findCalender(id);

        calender.update(calenderRequestDto);
        return id;

    }

    public Long deleteCalender(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Calender calender = findCalender(id);

        calenderRepository.delete(calender);
        return id;

    }

    private Calender findCalender(Long id) {
        return calenderRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }

}
