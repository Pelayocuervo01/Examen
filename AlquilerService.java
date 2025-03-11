package com.example.demo.Service;

import com.example.demo.Model.Alquiler;
import com.example.demo.Model.Coche;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.AlquilerRepository;
import com.example.demo.Repository.CocheRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlquilerService {
    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private CocheRepository cocheRepository;

    @Autowired
    private UserRepository usuarioRepository;

    // Crear un alquiler (alquilar un coche)
    public Alquiler alquilarCoche(Long userId, String plateNumber) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(userId);
        Optional<Coche> cocheOpt = cocheRepository.findByPlateNumber(plateNumber);

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Cambiar de IsAvailable() a getIsAvailable()
        if (cocheOpt.isEmpty() || !cocheOpt.get().getAvailable()) {
            throw new RuntimeException("Coche no disponible");
        }

        Coche coche = cocheOpt.get();
        coche.setAvailable(false);  // Marcar coche como no disponible

        Alquiler alquiler = new Alquiler();
        alquiler.setUser(usuarioOpt.get());
        alquiler.setCar(coche);
        alquiler.setRentalDate(LocalDateTime.now());
        alquiler.setStatus(Alquiler.Status.ACTIVE);

        // Guardar alquiler y actualizar coche
        cocheRepository.save(coche);
        return alquilerRepository.save(alquiler);
    }

    // Devolver coche (actualizar disponibilidad)
    public Alquiler devolverCoche(Long alquilerId) {
        Optional<Alquiler> alquilerOpt = alquilerRepository.findById(alquilerId);

        if (alquilerOpt.isEmpty()) {
            throw new RuntimeException("Alquiler no encontrado");
        }

        Alquiler alquiler = alquilerOpt.get();
        alquiler.setStatus(Alquiler.Status.COMPLETED);
        Coche coche = alquiler.getCar();
        coche.setAvailable(true);  // Marcar coche como disponible

        cocheRepository.save(coche);
        return alquilerRepository.save(alquiler);
    }

    // Obtener historial de alquileres de un usuario
    public List<Alquiler> obtenerHistorialDeAlquileres(Long userId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(userId);

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return alquilerRepository.findByUser(usuarioOpt.get());
    }
}
