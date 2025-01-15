package com.example.domain.seat.repository;

import com.example.domain.seat.entity.Seat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository {

    Seat findById(Long id);

    List<Seat> findAll();

    void save(Seat seat);
}
