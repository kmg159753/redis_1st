package com.example.infra.movie.repository;

import com.example.domain.movie.entity.Movie;
import com.example.domain.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {
    private final MovieJpaRepository movieJpaRepository;

    @Override
    public Movie findById(Long id) {
        return movieJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> findAll() {
        return movieJpaRepository.findAll();
    }

    @Override
    public void save(Movie movie) {
        movieJpaRepository.save(movie);
    }

    @Override
    public void saveAll(List<Movie> movies) {
        movieJpaRepository.saveAll(movies);
    }
}
