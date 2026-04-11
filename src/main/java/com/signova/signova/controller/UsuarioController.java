package com.signova.signova.controller;

import com.signova.signova.model.Usuario;
import com.signova.signova.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones CRUD de la entidad Usuario.
 * Permite crear, consultar, actualizar y eliminar usuarios en la base de datos.
 */
@RestController
@RequestMapping("/usuarios") // Ruta base para todos los endpoints
public class UsuarioController {

    // Inyección del repositorio para acceder a la base de datos
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Obtener todos los usuarios
     * Método: GET
     * URL: http://localhost:8080/usuarios
     */
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    /**
     * Buscar un usuario por su ID
     * Método: GET
     * URL: http://localhost:8080/usuarios/{id}
     */
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Crear un nuevo usuario
     * Método: POST
     * URL: http://localhost:8080/usuarios
     */
    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Actualizar un usuario existente
     * Método: PUT
     * URL: http://localhost:8080/usuarios/{id}
     */
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId_usuario(id); // Asigna el ID al usuario a actualizar
        return usuarioRepository.save(usuario);
    }

    /**
     * Eliminar un usuario por su ID
     * Método: DELETE
     * URL: http://localhost:8080/usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }
}