package com.example.infra.movie.repository;

import com.example.domain.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieJpaRepository extends JpaRepository<Movie,Long> {

}
