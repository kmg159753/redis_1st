package com.example.domain.screening.repository;


import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening,Long>,ScreeningRepositoryCustom {


    @Query("SELECT s FROM Screening s " +
            "JOIN FETCH s.movie m " +
            "JOIN FETCH s.theater t " +
            "WHERE s.startTime > :now " +
            "ORDER BY s.startTime ASC"
    )
    List<Screening> findScreeningWithDetailsByStartTimeAfter(@Param("now") LocalDateTime now);

//    @Query("SELECT s FROM Screening s " +
//            "JOIN FETCH s.movie m " +
//            "JOIN FETCH s.theater t " +
//            "WHERE s.startTime > :now " +
//            "AND (:title IS NULL OR m.title = :title) " +
//            "AND (:genre IS NULL OR m.genre = :genre) " +
//            "ORDER BY s.startTime ASC")
//    List<Screening> findScreeningInfoAndSearchingByTitleAndGenre(
//            @Param("now") LocalDateTime now,
//            @Param("title") String title,
//            @Param("genre") Movie.Genre genre);

    @Query()
    List<Screening> findScreeningByStartTimeAfter(LocalDateTime now);
}
