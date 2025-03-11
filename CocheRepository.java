package com.example.demo.Repository;
import com.example.demo.Model.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long> {
    List<Coche> findByBrandContainingIgnoreCase(String brand);
    List<Coche> findByAvailableTrue();
    Optional<Coche> findByPlateNumber(String plateNumber);

    void deleteByPlateNumber(String plateNumber);

    List<Coche> findByModelContainingIgnoreCase(String model);

    List<Coche> findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model);
}
