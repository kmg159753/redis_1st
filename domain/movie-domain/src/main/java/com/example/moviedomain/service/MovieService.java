package com.example.moviedomain.service;

import com.example.moviedomain.dto.MovieResponseDto;
import com.example.moviedomain.entity.Movie;
import com.example.moviedomain.entity.Screening;
import com.example.moviedomain.entity.Theater;
import com.example.moviedomain.repository.MovieRepository;
import com.example.moviedomain.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;

    public List<MovieResponseDto> getMovies() {

        LocalDateTime now = LocalDateTime.now();

        // 상영 중인 영화 조회
        List<Screening> screenings = screeningRepository.findScreeningByStartTimeAfter(now);

        // 상영 정보를 기반으로 DTO 생성
        return screenings.stream()
                .filter(screening -> screening.getMovie().getReleaseDate().isBefore(screening.getStartTime())) // 개봉일 검증
                .sorted((s1, s2) -> s2.getMovie().getReleaseDate().compareTo(s1.getMovie().getReleaseDate())) // 개봉일 내림차순
                .map(screening -> {
                    Movie movie = screening.getMovie();
                    Theater theater = screening.getTheater();

                    List<MovieResponseDto.Schedule> timeTable = screenings.stream()
                            .filter(s -> s.getMovie().getId().equals(movie.getId()))
                            .sorted((t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()))
                            .map(s -> new MovieResponseDto.Schedule(s.getStartTime(), s.getEndTime()))
                            .collect(Collectors.toList());

                    return MovieResponseDto.builder()
                            .title(movie.getTitle())
                            .rating(movie.getRating().toString())
                            .releaseDate(movie.getReleaseDate())
                            .thumbnailUrl(movie.getThumbnail())
                            .runtimeMinutes(movie.getRuntimeMinutes())
                            .genre(movie.getGenre().toString())
                            .theaterName(theater.getName())
                            .scheduleList(timeTable)
                            .build();
                })
                .collect(Collectors.toList());
    }
}


