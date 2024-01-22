package com.sparta.calender.controller;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.entity.Calender;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calender")
public class CalenderController {

    private final JdbcTemplate jdbcTemplate;

    public CalenderController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/")
    public CalenderResponseDto createCalender(@RequestBody CalenderRequestDto calenderRequestDto) {

        // calenderRequestDto -> calenderEntity 로 변환
        Calender calender = new Calender(calenderRequestDto);

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO calender (title, content, writer, password, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, calender.getTitle());
                    preparedStatement.setString(2, calender.getContent());
                    preparedStatement.setString(3, calender.getWriter());
                    preparedStatement.setString(4, String.valueOf(calender.getPassword()));
                    preparedStatement.setString(5, calender.getDate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        long id = keyHolder.getKey().longValue();
        calender.setId(id);

        // Entity -> responseDto
        CalenderResponseDto calenderResponseDto = new CalenderResponseDto(calender);

        return calenderResponseDto;
    }

    @GetMapping("/")
    public List<CalenderResponseDto> readCalenders() {
        // DB 조회
        String sql = "SELECT * FROM calender";

        return jdbcTemplate.query(sql, new RowMapper<CalenderResponseDto>() {
            @Override
            public CalenderResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                String password = rs.getString("password");
                String date = rs.getString("date");
                return new CalenderResponseDto(id, title, content, writer, password, date);
            }
        });
    }


    @PutMapping("/{id}")
    public Long updateCalender(@PathVariable Long id, @RequestBody CalenderRequestDto calenderRequestDto) {

        // 해당 메모가 DB에 존재하는지 확인
        Calender calender = findById(id);

        if(calender != null) {
            // memo 내용 수정
            String sql = "UPDATE calender SET title = ?, content = ?, writer = ?, password = ?, date = ? WHERE id = ?";
            jdbcTemplate.update(sql, calenderRequestDto.getTitle(), calenderRequestDto.getContent(), calenderRequestDto.getWriter(), calenderRequestDto.getPassword(),calenderRequestDto.getDate(),id);

            return id;
        } else {
            throw new InputMismatchException("선택한 일정은 존재하지 않습니다.");
        }
    }


    @DeleteMapping("/{id}")
    public Long deleteCalender(@PathVariable Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Calender calender = findById(id);

        if(calender != null) {
            // memo 내용 수정
            String sql = "DELETE FROM calender WHERE id = ?";
            jdbcTemplate.update(sql, id);

            return id;
        } else {
            throw new InputMismatchException("선택한 일정은 존재하지 않습니다.");
        }
    }

    private Calender findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM calender WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Calender calender = new Calender();
                calender.setTitle(resultSet.getString("title"));
                calender.setContent(resultSet.getString("content"));
                calender.setWriter(resultSet.getString("writer"));
                calender.setPassword(Integer.parseInt(resultSet.getString("password")));
                calender.setDate(resultSet.getString("date"));
                return calender;
            } else {
                return null;
            }
        }, id);
    }

}
