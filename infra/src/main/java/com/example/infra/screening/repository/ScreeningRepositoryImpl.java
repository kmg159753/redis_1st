package com.example.infra.screening.repository;

import com.example.domain.screening.entity.Screening;
import com.example.domain.screening.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScreeningRepositoryImpl implements ScreeningRepository {
    private final ScreeningJpaRepository screeningJpaRepository;

    @Override
    public List<Screening> findScreeningByStartTimeAfter(LocalDateTime now) {
        return screeningJpaRepository.findScreeningByStartTimeAfter(now);
    }

    @Override
    public List<Screening> findScreeningWithDetailsByStartTimeAfter(LocalDateTime now) {
        return screeningJpaRepository.findScreeningWithDetailsByStartTimeAfter(now);
    }

    @Override
    public Screening findById(Long id) {
        return screeningJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Screening> findAll() {
        return screeningJpaRepository.findAll();
    }

    @Override
    public void save(Screening screening) {
        screeningJpaRepository.save(screening);
    }

    @Override
    public void saveAll(List<Screening> screenings) {
        screeningJpaRepository.saveAll(screenings);
    }
}
