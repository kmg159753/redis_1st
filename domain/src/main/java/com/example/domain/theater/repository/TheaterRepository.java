package com.example.domain.theater.repository;


import com.example.domain.movie.entity.Movie;
import com.example.domain.seat.entity.Seat;
import com.example.domain.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository {

    Theater findById(Long id);

    List<Theater> findAll();

    void save(Theater theater);

    void saveAll(List<Theater> theaters);
}
