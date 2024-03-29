package com.sparta.calender.repository;

import com.sparta.calender.entity.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalenderRepository extends JpaRepository<Calender, Long> {

    List<Calender> findAllByOrderByModifiedAtDesc();
}