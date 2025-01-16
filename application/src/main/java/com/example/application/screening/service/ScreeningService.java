package com.example.application.screening.service;


import com.example.domain.screening.dto.ScreeningResponseDto;
import com.example.domain.screening.entity.Screening;
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


    public List<Screening> getScreengings() {

        LocalDateTime now = LocalDateTime.now();

        // 상영 중인 영화 조회
        return screeningRepository.findScreeningWithDetailsByStartTimeAfter(now);
    }
}


