package com.example.application.screening.service;


import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.entity.Screening;
import com.example.domain.screening.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;


    public List<Screening> getScreengings(String title, String genre) {

        Movie.Genre genreEnum = null;
        if (genre != null && !genre.isBlank()) {
            genreEnum = Movie.Genre.valueOf(genre.toUpperCase()); // 문자열을 Enum으로 변환
        }


        LocalDateTime now = LocalDateTime.now();
        // 상영 중인 영화 조회
        return screeningRepository.findScreeningInfoAndSearchingByTitleAndGenre(now,title,genreEnum);
    }
}


