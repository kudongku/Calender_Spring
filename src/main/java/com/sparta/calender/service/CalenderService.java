package com.sparta.calender.service;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.dto.PasswordDto;
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

    public void createCalender(CalenderRequestDto calenderRequestDto) {
        // calenderRequestDto -> calenderEntity 로 변환
        Calender calender = new Calender(calenderRequestDto);

        // DB 저장
        calenderRepository.save(calender);
    }

    public List<CalenderResponseDto> getCalenders() {
        return calenderRepository
                .findAllByOrderByModifiedAtDesc()
                .stream()
                .map(CalenderResponseDto::new)
                .toList();
    }

    @Transactional
    public boolean updateCalender(Long id, CalenderRequestDto calenderRequestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Calender calender = findCalender(id);

        if(calenderRequestDto.getPassword().equals(calender.getPassword())){
            calender.update(calenderRequestDto);
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteCalender(Long id, PasswordDto passwordDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Calender calender = findCalender(id);

        if(passwordDto.getPassword().equals(calender.getPassword())){
            calenderRepository.delete(calender);
            return true;
        }else{
            return false;
        }
    }

    private Calender findCalender(Long id) {
        return calenderRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }

    public CalenderResponseDto showCalenderDetails(Long id) {
        Calender calender = findCalender(id);
        return new CalenderResponseDto(calender);
    }
}
