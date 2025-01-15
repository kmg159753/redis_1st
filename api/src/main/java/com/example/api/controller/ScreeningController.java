package com.example.api.controller;


import com.example.application.screening.service.ScreeningService;
import com.example.common.util.dto.DataResponse;
import com.example.domain.screening.dto.ScreeningResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

//    @GetMapping("/screening")
//    public ResponseEntity<DataResponse<List<ScreeningResponseDto>>> getMovies() {
//
//        List<ScreeningResponseDto> movieData = screeningService.getMovies();
//
//        DataResponse<List<ScreeningResponseDto>> response = DataResponse.response(true, movieData);
//
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/screenings")
    public ResponseEntity<DataResponse<List<ScreeningResponseDto>>> getScreenings() {

        List<ScreeningResponseDto> movieData = screeningService.getScreengings();

        DataResponse<List<ScreeningResponseDto>> response = DataResponse.response(true, movieData);

        return ResponseEntity.ok(response);
    }
}
