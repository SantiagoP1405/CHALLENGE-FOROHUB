package com.santiagogomez.challenge.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.santiagogomez.challenge.forohub.domain.usuario.DatosRegistroUsuario;
import com.santiagogomez.challenge.forohub.domain.usuario.Usuario;
import com.santiagogomez.challenge.forohub.domain.usuario.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping
    private ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistro){
        if (usuarioRepository.existsByNombre(datosRegistro.nombre())) {
            return ResponseEntity.badRequest().body("El nombre ya está registrado.");
        }

        if (usuarioRepository.existsByEmail(datosRegistro.email())) {
            return ResponseEntity.badRequest().body("El email ya está registrado.");
        }
        String hashPassword = passwordEncoder.encode(datosRegistro.password());
        Usuario usuario = new Usuario(datosRegistro,hashPassword);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado exitosamente. Tu id de usuario es: " + usuario.getId());
    }
}
