package com.example.application.screening.service;



import com.example.domain.screening.dto.ScreeningResponseDto;
import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.entity.Screening;
import com.example.domain.theater.entity.Theater;
import com.example.domain.movie.repository.MovieRepository;
import com.example.domain.screening.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

//    public List<ScreeningResponseDto> getMovies() {
//
//        LocalDateTime now = LocalDateTime.now();
//
//        // 상영 중인 영화 조회
//        List<Screening> screenings = screeningRepository.findScreeningWithDetailsByStartTimeAfter(now);
//
//        // 상영 정보를 기반으로 DTO 생성
//        return screenings.stream()
//                .filter(screening -> screening.getMovie().getReleaseDate().isBefore(screening.getStartTime())) // 개봉일 검증
//                .sorted((s1, s2) -> s2.getMovie().getReleaseDate().compareTo(s1.getMovie().getReleaseDate())) // 개봉일 내림차순
//                .map(screening -> {
//                    Movie movie = screening.getMovie();
//                    Theater theater = screening.getTheater();
//
//                    List<ScreeningResponseDto.Schedule> timeTable = screenings.stream()
//                            .filter(s -> s.getMovie().getId().equals(movie.getId()))
//                            .sorted((t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()))
//                            .map(s -> new ScreeningResponseDto.Schedule(s.getStartTime(), s.getEndTime()))
//                            .collect(Collectors.toList());
//
//                    return ScreeningResponseDto.builder()
//                            .title(movie.getTitle())
//                            .rating(movie.getRating().toString())
//                            .releaseDate(movie.getReleaseDate())
//                            .thumbnailUrl(movie.getThumbnail())
//                            .runtimeMinutes(movie.getRuntimeMinutes())
//                            .genre(movie.getGenre().toString())
//                            .theaterName(theater.getName())
//                            .scheduleList(timeTable)
//                            .build();
//                })
//                .collect(Collectors.toList());
//    }

    public List<ScreeningResponseDto> getScreengings() {

        LocalDateTime now = LocalDateTime.now();

        // 상영 중인 영화 조회
        List<Screening> screenings = screeningRepository.findScreeningWithDetailsByStartTimeAfter(now);

        // 영화별 그룹화
        Map<Long, List<Screening>> screeningsGroupedByMovie = screenings.stream()
                .filter(screening -> screening.getMovie().getReleaseDate().isBefore(screening.getStartTime())) // 개봉일 검증
                .collect(Collectors.groupingBy(screening -> screening.getMovie().getId()));

        // DTO 생성
        return screeningsGroupedByMovie.entrySet().stream()
                .map(entry -> {
                    List<Screening> movieScreenings = entry.getValue();
                    Screening firstScreening = movieScreenings.get(0); // 해당 영화의 첫 상영 정보
                    return ScreeningResponseDto.builder()
                            .title(firstScreening.getMovie().getTitle())
                            .rating(firstScreening.getMovie().getRating().toString())
                            .releaseDate(firstScreening.getMovie().getReleaseDate())
                            .thumbnailUrl(firstScreening.getMovie().getThumbnail())
                            .runtimeMinutes(firstScreening.getMovie().getRuntimeMinutes())
                            .genre(firstScreening.getMovie().getGenre().toString())
                            .theaterSchedules(
                                    movieScreenings.stream()
                                            .collect(Collectors.groupingBy(
                                                    screening -> screening.getTheater().getName(), // 상영관별 그룹화
                                                    Collectors.mapping(
                                                            screening -> new ScreeningResponseDto.TheaterSchedule.Schedule(
                                                                    screening.getStartTime(),
                                                                    screening.getEndTime()
                                                            ),
                                                            Collectors.toList()
                                                    )
                                            ))
                                            .entrySet().stream()
                                            .map(entry2 -> ScreeningResponseDto.TheaterSchedule.builder()
                                                    .theaterName(entry2.getKey()) // 상영관 이름
                                                    .schedules(entry2.getValue()) // 시간표 리스트
                                                    .build()
                                            )
                                            .collect(Collectors.toList())
                            )
                            .build();
                })
                .sorted(Comparator.comparing(ScreeningResponseDto::getReleaseDate).reversed()) // 개봉일 내림차순 정렬
                .collect(Collectors.toList());
    }
}


