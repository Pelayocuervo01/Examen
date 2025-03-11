package com.example.demo.Repository;

import com.example.demo.Model.Alquiler;
import com.example.demo.Model.Usuario;
import com.example.demo.Model.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByUser(Usuario user);

}
