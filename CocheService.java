package com.example.demo.Service;

import com.example.demo.Model.Coche;
import com.example.demo.Repository.CocheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CocheService {
    @Autowired
    private CocheRepository CocheRepository;

    // Crear un nuevo coche
    public Coche crearCoche (Coche coche) {
        return CocheRepository.save(coche);
    }

    // Obtener todos los coches alamcenados
    public List<Coche> obtenerCoches() {
        return CocheRepository.findAll();
    }

    // Obtener un coche por la plate number
    public Optional<Coche> obtenerCochePorPlateNumber(String plateNumber) {
        return CocheRepository.findByPlateNumber(plateNumber);
    }

    // Actualizar los datos de un Coche
    public Coche actualizarUsuario(Long id, Coche cocheActualizado) {
        return CocheRepository.findById(id).map(coche -> {
            coche.setPlateNumber(cocheActualizado.getPlateNumber());
            coche.setBrand(cocheActualizado.getBrand());
            coche.setModel(cocheActualizado.getModel());
            return CocheRepository.save(coche);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Eliminar un coche por plate number
    public void eliminarCochePorPlateNumber(String plateNumber) {
        CocheRepository.deleteByPlateNumber(plateNumber);
    }

    // Comprobar si el coche esta disponible
    public List<Coche> obtenerCochesDisponibles() {
        return CocheRepository.findByAvailableTrue();
    }

    public List<Coche> buscarCochesPorMarcaOModelo(String brand, String model) {
        // Si ambos parámetros son nulos, devolver todos los coches
        if (brand == null && model == null) {
            return CocheRepository.findAll();
        }

        // Si solo brand es proporcionada
        if (brand != null && model == null) {
            return CocheRepository.findByBrandContainingIgnoreCase(brand);
        }

        // Si solo model es proporcionado
        if (brand == null && model != null) {
            return CocheRepository.findByModelContainingIgnoreCase(model);
        }

        // Si ambos parámetros están proporcionados, buscar por ambos
        return CocheRepository.findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(brand, model);
    }

    public void eliminarCoche(String plateNumber) {
        Optional<Coche> cocheOpt = CocheRepository.findByPlateNumber(plateNumber);

        // Verificamos si el coche existe
        if (cocheOpt.isPresent()) {
            CocheRepository.delete(cocheOpt.get()); // Eliminar coche
        } else {
            throw new RuntimeException("Coche no encontrado con el plateNumber: " + plateNumber);
        }
    }

    public Coche actualizarCoche(Coche coche) {
        Optional<Coche> cocheOpt = CocheRepository.findByPlateNumber(coche.getPlateNumber());

        // Verificamos si el coche existe
        if (cocheOpt.isPresent()) {
            Coche cocheExistente = cocheOpt.get();

            // Actualizamos los campos del coche existente con los valores del coche recibido
            cocheExistente.setBrand(coche.getBrand());
            cocheExistente.setModel(coche.getModel());
            cocheExistente.setYear(coche.getYear());
            cocheExistente.setAvailable(coche.getAvailable());
            cocheExistente.setApellido(coche.getApellido());

            // Guardamos los cambios
            return CocheRepository.save(cocheExistente);
        } else {
            throw new RuntimeException("Coche no encontrado con el plateNumber: " + coche.getPlateNumber());
        }
    }

}
