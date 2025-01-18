package com.example.api.screening.controller;


import com.example.api.screening.dto.ScreeningResponseDto;
import com.example.api.screening.mapper.ScreeningDtoMapper;
import com.example.application.screening.service.ScreeningService;
import com.example.common.util.dto.DataResponse;
import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.entity.Screening;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;


    @GetMapping("/screenings")
    public ResponseEntity<DataResponse<List<ScreeningResponseDto>>> getScreenings(
            @RequestParam(required = false) String title,
            @RequestParam(required = false)
            @Pattern(
                    regexp = "|ACTION|ROMANCE|HORROR|SF",
                    message = "Genre must be one of the following: ACTION, ROMANCE, HORROR, SF"
            ) String genre) {

        List<Screening> screeningList = screeningService.getScreengings(title, genre);

        List<ScreeningResponseDto> screeningData = ScreeningDtoMapper.toScreeningResponseDto(screeningList);

        DataResponse<List<ScreeningResponseDto>> response = DataResponse.response(true, screeningData);

        return ResponseEntity.ok(response);
    }
}
