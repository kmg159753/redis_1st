package com.example.moviedomain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "Seat")
@Getter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;
}
