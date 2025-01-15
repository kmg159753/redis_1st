package com.example.domain.seat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import com.example.domain.screening.entity.Screening;

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
