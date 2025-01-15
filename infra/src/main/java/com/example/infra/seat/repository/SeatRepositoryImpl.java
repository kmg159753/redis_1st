package com.example.infra.seat.repository;

import com.example.domain.seat.entity.Seat;
import com.example.domain.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {
    private final SeatJpaRepository seatJpaRepository;

    @Override
    public Seat findById(Long id) {
        return seatJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Seat> findAll() {
        return seatJpaRepository.findAll();
    }

    @Override
    public void save(Seat seat) {
        seatJpaRepository.save(seat);
    }
}
