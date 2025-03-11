package com.example.demo.Controller;

import com.example.demo.Model.Alquiler;
import com.example.demo.Service.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/postgres")  // Aquí cambiamos la ruta base
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;

    // Endpoint para alquilar un coche (POST /api/v1/postgres/rentals)
    @PostMapping("/rentals")
    public ResponseEntity<Alquiler> alquilarCoche(@RequestParam Long userId, @RequestParam String plateNumber) {
        Alquiler alquiler = alquilerService.alquilarCoche(userId, plateNumber);
        return ResponseEntity.ok(alquiler);
    }

    // Endpoint para devolver un coche (PUT /api/v1/postgres/rentals/{id}/return)
    @PutMapping("/rentals/{id}/return")
    public ResponseEntity<Alquiler> devolverCoche(@PathVariable Long id) {
        Alquiler alquiler = alquilerService.devolverCoche(id);
        return ResponseEntity.ok(alquiler);
    }

    // Endpoint para obtener el historial de alquileres de un usuario (GET /api/v1/postgres/users/{id}/rentals)
    @GetMapping("/users/{id}/rentals")
    public ResponseEntity<List<Alquiler>> obtenerHistorialDeAlquileres(@PathVariable Long id) {
        List<Alquiler> historial = alquilerService.obtenerHistorialDeAlquileres(id);
        return ResponseEntity.ok(historial);
    }
}
