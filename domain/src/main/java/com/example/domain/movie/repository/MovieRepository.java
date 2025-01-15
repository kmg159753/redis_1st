package com.example.domain.movie.repository;


import com.example.domain.movie.entity.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {


    Movie findById(Long id);

    List<Movie> findAll();

    void save(Movie movie);

    void saveAll(List<Movie> movies);
}
