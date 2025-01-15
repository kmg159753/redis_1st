package com.example.infra.theater.repository;

import com.example.domain.theater.entity.Theater;
import com.example.domain.theater.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TheaterRepositoryImpl implements TheaterRepository {
    private final TheaterJpaRepository theaterJpaRepository;


    @Override
    public Theater findById(Long id) {
        return theaterJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Theater> findAll() {
        return theaterJpaRepository.findAll();
    }

    @Override
    public void save(Theater theater) {
        theaterJpaRepository.save(theater);
    }

    @Override
    public void saveAll(List<Theater> theaters) {
        theaterJpaRepository.saveAll(theaters);
    }
}
