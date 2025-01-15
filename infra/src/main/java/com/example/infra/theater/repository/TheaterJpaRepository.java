package com.example.infra.theater.repository;

import com.example.domain.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterJpaRepository extends JpaRepository<Theater,Long> {

}
