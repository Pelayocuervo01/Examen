package com.example.demo.Service;

import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UserRepository UserRepository;

    // Crear un nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        return UserRepository.save(usuario);
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        return UserRepository.findAll();
    }

    // Obtener un usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long userid) {
        return UserRepository.findById(userid);
    }

    // Actualizar un usuario
    public Usuario actualizarUsuario(Long userid, Usuario usuarioActualizado) {
        return UserRepository.findById(userid).map(usuario -> {
            usuario.setUsername(usuarioActualizado.getUsername());
            usuario.setEmail(usuarioActualizado.getEmail());
            return UserRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Eliminar un usuario por ID
    public void eliminarUsuario(Long userid) {
        UserRepository.deleteById(userid);
    }
}
