package com.example.api.screening.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class ScreeningResponseDto {
    private String title;
    private String thumbnailUrl;
    private String rating;
    private LocalDateTime releaseDate;
    private int runtimeMinutes;
    private String genre;
    private List<TheaterSchedule> theaterSchedules; // 상영관별 시간표 리스트

    @Data
    @Builder
    public static class TheaterSchedule {
        private String theaterName; // 상영관 이름
        private List<Schedule> schedules; // 해당 상영관의 상영 시간표

        @Data
        @AllArgsConstructor
        public static class Schedule {
            private LocalDateTime startTime; // 상영 시작 시간
            private LocalDateTime endTime;   // 상영 종료 시간
        }
    }
}