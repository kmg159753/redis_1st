package com.example.domain.screening.dto;

import com.example.domain.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ProjectionScreeningResponseDto {

    private String title;
    private String thumbnailUrl;
    private Movie.Rating rating;
    private LocalDateTime releaseDate;
    private int runtimeMinutes;
    private Movie.Genre genre;
    private String theaterName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
