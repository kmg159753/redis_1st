package com.example.domain.screening.repository;

import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.entity.Screening;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepositoryCustom {
    List<Screening> findScreeningInfoAndSearchingByTitleAndGenre(LocalDateTime now,String title,Movie.Genre genre);
}
