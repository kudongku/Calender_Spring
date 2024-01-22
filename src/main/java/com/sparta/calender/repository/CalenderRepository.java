package com.sparta.calender.repository;

import com.sparta.calender.dto.CalenderRequestDto;
import com.sparta.calender.dto.CalenderResponseDto;
import com.sparta.calender.entity.Calender;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CalenderRepository {

    private final JdbcTemplate jdbcTemplate;

    public Calender save(Calender calender) {

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

        return calender;
    }

    public List<CalenderResponseDto> findAll() {
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



    public void update(Long id, CalenderRequestDto calenderRequestDto) {
        // memo 내용 수정
        String sql = "UPDATE calender SET title = ?, content = ?, writer = ?, password = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(sql, calenderRequestDto.getTitle(), calenderRequestDto.getContent(), calenderRequestDto.getWriter(), calenderRequestDto.getPassword(), calenderRequestDto.getDate(), id);
    }

    public void remove(Long id) {
        // memo 내용 수정
        String sql = "DELETE FROM calender WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Calender findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM calender WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
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
