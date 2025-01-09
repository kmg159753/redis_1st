package com.example.appapi.controller;


import com.example.common.util.dto.DataResponse;
import com.example.moviedomain.dto.MovieResponseDto;
import com.example.moviedomain.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<DataResponse<List<MovieResponseDto>>> getMovies() {

        List<MovieResponseDto> movieData = movieService.getMovies();

        DataResponse<List<MovieResponseDto>> response = DataResponse.response(true, movieData);

        return ResponseEntity.ok(response);
    }
}
