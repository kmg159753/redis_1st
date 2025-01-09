package com.example.moviedomain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MovieResponseDto {
    private String title;
    private String thumbnailUrl;
    private String rating;
    private LocalDateTime releaseDate;
    private int runtimeMinutes;
    private String genre;
    private String theaterName;
    private List<Schedule> scheduleList;

    @Data
    @AllArgsConstructor
    public static class Schedule {
        private LocalDateTime startTime; // 상영 시작 시간
        private LocalDateTime endTime;   // 상영 종료 시간
    }
}
