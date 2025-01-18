package com.example.api.screening.mapper;

import com.example.api.screening.dto.ScreeningResponseDto;
import com.example.domain.screening.entity.Screening;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScreeningDtoMapper {

    public static List<ScreeningResponseDto> toScreeningResponseDto(List<Screening> screenings){
        //  영화별 상영정보 그룹화
        Map<Long, List<Screening>> screeningsGroupedByMovie = screenings.stream()
                .filter(screening -> screening.getMovie().getReleaseDate().isBefore(screening.getStartTime()))
                .collect(Collectors.groupingBy(screening -> screening.getMovie().getId()));

        return screeningsGroupedByMovie.entrySet().stream()
                .map(entry -> {
                    List<Screening> screeningsAboutMovie = entry.getValue();
                    Screening firstScreening = screeningsAboutMovie.get(0);

                    return ScreeningResponseDto.builder()
                            .title(firstScreening.getMovie().getTitle())
                            .rating(firstScreening.getMovie().getRating().toString())
                            .releaseDate(firstScreening.getMovie().getReleaseDate())
                            .thumbnailUrl(firstScreening.getMovie().getThumbnail())
                            .runtimeMinutes(firstScreening.getMovie().getRuntimeMinutes())
                            .genre(firstScreening.getMovie().getGenre().toString())
                            .theaterSchedules(
                                    screeningsAboutMovie.stream()
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
                            ).build();

                }).sorted(Comparator.comparing(ScreeningResponseDto::getReleaseDate).reversed())// 개봉일 내림차순 정렬
                .collect(Collectors.toList());
    }
}
