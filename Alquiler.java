package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Alquiler")
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "cocheid", nullable = false)
    private Coche car;

    @Column(nullable = false)
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Pelayo'")
    private String minombre;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Pelayo'")
    private String minombre2;

    public enum Status {
        ACTIVE, COMPLETED
    }
}
