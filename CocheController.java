package com.example.demo.Controller;

import com.example.demo.Model.Coche;
import com.example.demo.Service.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/coches")
public class CocheController {

    @Autowired
    private CocheService cocheService;

    // Crear un nuevo coche
    @PostMapping
    public ResponseEntity<Coche> crearCoche(@RequestBody Coche coche) {
        try {
            Coche nuevoCoche = cocheService.crearCoche(coche);
            return new ResponseEntity<>(nuevoCoche, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener todos los coches
    @GetMapping
    public ResponseEntity<List<Coche>> obtenerCoches() {
        List<Coche> coches = cocheService.obtenerCoches();
        if (coches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(coches, HttpStatus.OK);
    }

    // Obtener coche por ID (plateNumber)
    @GetMapping("/{plateNumber}")
    public ResponseEntity<Coche> obtenerCochePorPlateNumber(@PathVariable("plateNumber") String plateNumber) {
        Optional<Coche> coche = cocheService.obtenerCochePorPlateNumber(plateNumber);
        if (coche.isPresent()) {
            return new ResponseEntity<>(coche.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar un coche por plateNumber
    @PutMapping("/{plateNumber}")
    public ResponseEntity<Coche> actualizarCoche(@PathVariable("plateNumber") String plateNumber, @RequestBody Coche cocheDetails) {
        Optional<Coche> cocheOpt = cocheService.obtenerCochePorPlateNumber(plateNumber);

        if (cocheOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Coche coche = cocheOpt.get();
        coche.setBrand(cocheDetails.getBrand());
        coche.setModel(cocheDetails.getModel());
        coche.setYear(cocheDetails.getYear());
        coche.setAvailable(cocheDetails.getAvailable());
        coche.setApellido(cocheDetails.getApellido());

        Coche cocheActualizado = cocheService.actualizarCoche(coche);
        return new ResponseEntity<>(cocheActualizado, HttpStatus.OK);
    }

    // Eliminar un coche por plateNumber
    @DeleteMapping("/{plateNumber}")
    public ResponseEntity<HttpStatus> eliminarCoche(@PathVariable("plateNumber") String plateNumber) {
        try {
            cocheService.eliminarCoche(plateNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
