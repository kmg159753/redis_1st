package com.example.infra.screening.repository;

import com.example.domain.screening.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningJpaRepository extends JpaRepository<Screening,Long> {


    @Query("SELECT s FROM Screening s " +
            "JOIN FETCH s.movie m " +
            "JOIN FETCH s.theater t " +
            "WHERE s.startTime > :now")
    List<Screening> findScreeningWithDetailsByStartTimeAfter(@Param("now") LocalDateTime now);
    @Query()
    List<Screening> findScreeningByStartTimeAfter(LocalDateTime now);
}
