package com.example.domain;


import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.entity.Screening;
import com.example.domain.theater.entity.Theater;
import com.example.domain.movie.repository.MovieRepository;
import com.example.domain.screening.repository.ScreeningRepository;
import com.example.domain.theater.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ScreeningRepository screeningRepository;

    private final Random random = new Random();

//    @PostConstruct
    public void init() {
        // 1. 영화 데이터 생성
        List<Movie> movies = createMovies();
        movieRepository.saveAll(movies);

        // 2. 상영관 데이터 생성
        List<Theater> theaters = createTheaters();
        theaterRepository.saveAll(theaters);

        // 3. 상영 정보 데이터 생성
        List<Screening> screenings = createScreenings(movies, theaters);
        screeningRepository.saveAll(screenings);

        System.out.println("Initial data created!");
    }

    private List<Movie> createMovies() {
        List<Movie> movies = new ArrayList<>();
        for (int i = 1; i <= 500; i++) {
            movies.add(Movie.builder()
                    .title("Movie " + i)
                    .rating(Movie.Rating.values()[random.nextInt(Movie.Rating.values().length)])
                    .releaseDate(LocalDateTime.now().minusDays(random.nextInt(365)))
                    .thumbnail("https://example.com/thumbnails/movie" + i + ".jpg")
                    .genre(Movie.Genre.values()[random.nextInt(Movie.Genre.values().length)])
                    .runtimeMinutes(90 + random.nextInt(30)) // 90 ~ 120분
                    .build());
        }
        return movies;
    }

    private List<Theater> createTheaters() {
        List<Theater> theaters = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            theaters.add(Theater.builder()
                    .name("Theater " + i)
                    .build());
        }
        return theaters;
    }

    private List<Screening> createScreenings(List<Movie> movies, List<Theater> theaters) {
        List<Screening> screenings = new ArrayList<>();
        for (int i = 1; i <= 50000; i++) {
            Movie movie = movies.get(random.nextInt(movies.size()));
            Theater theater = theaters.get(random.nextInt(theaters.size()));
            LocalDateTime startTime = LocalDateTime.now().plusHours(random.nextInt(720)); // 현재부터 최대 30일 후
            screenings.add(Screening.builder()
                    .movie(movie)
                    .theater(theater)
                    .startTime(startTime)
                    .endTime(startTime.plusMinutes(movie.getRuntimeMinutes()))
                    .build());
        }
        return screenings;
    }
}
