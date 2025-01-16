package com.example.api.screening.controller;


import com.example.api.screening.mapper.ScreeningDtoMapper;
import com.example.application.screening.service.ScreeningService;
import com.example.common.util.dto.DataResponse;
import com.example.domain.screening.dto.ScreeningResponseDto;
import com.example.domain.screening.entity.Screening;
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


    @GetMapping("/screenings")
    public ResponseEntity<DataResponse<List<ScreeningResponseDto>>> getScreenings() {

        List<Screening> screengingList = screeningService.getScreengings();

        List<ScreeningResponseDto> screeningData = ScreeningDtoMapper.toScreeningResponseDto(screengingList);

        DataResponse<List<ScreeningResponseDto>> response = DataResponse.response(true, screeningData);

        return ResponseEntity.ok(response);
    }
}
