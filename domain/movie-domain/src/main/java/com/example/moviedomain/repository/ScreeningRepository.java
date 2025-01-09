package com.example.moviedomain.repository;

import com.example.moviedomain.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening,Long> {
    List<Screening> findScreeningByStartTimeAfter(LocalDateTime now);

}
