package com.signova.signova.controller;

import com.signova.signova.model.Usuario;
import com.signova.signova.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones CRUD de la entidad Usuario.
 */
@CrossOrigin(origins = "http://localhost:3000") // Permite conexión con React
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Obtener todos los usuarios
     */
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    /**
     * Buscar usuario por ID
     */
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Crear usuario (REGISTRO)
     */
    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * LOGIN DE USUARIO 🔐
     */
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {

        // Buscar usuario por email
        Usuario user = usuarioRepository.findByCorreo(usuario.getCorreo());

        // Validar credenciales
        if (user != null && user.getContraseña().equals(usuario.getContraseña())) {
            return "Autenticación satisfactoria";
        } else {
            return "Error en la autenticación";
        }
    }

    /**
     * Actualizar usuario
     */
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId_usuario(id);
        return usuarioRepository.save(usuario);
    }

    /**
     * Eliminar usuario
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }
}