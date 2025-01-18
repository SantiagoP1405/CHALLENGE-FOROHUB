package com.santiagogomez.challenge.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
    @PostMapping
    private ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroUsuario datosRegistro){
        usuarioRepository.save(new Usuario(datosRegistro));
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}
