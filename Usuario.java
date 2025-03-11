package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PCS'")
    private String iniciales;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PCS'")
    private String iniciales2;

}