package com.example.domain.screening.repository;


import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.entity.Screening;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository {
    List<Screening> findScreeningByStartTimeAfter(LocalDateTime now);

    List<Screening> findScreeningWithDetailsByStartTimeAfter(LocalDateTime now);

    Screening findById(Long id);

    List<Screening> findAll();

    void save(Screening screening);

    void saveAll(List<Screening> screenings);

}
