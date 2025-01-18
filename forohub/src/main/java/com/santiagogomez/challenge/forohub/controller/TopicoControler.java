package com.santiagogomez.challenge.forohub.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.santiagogomez.challenge.forohub.domain.topico.*;
import com.santiagogomez.challenge.forohub.domain.usuario.*;

import jakarta.validation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoControler {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
    Usuario usuario = usuarioRepository.findById(datos.idUsuario())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + datos.idUsuario()));
    
    Topico topico = topicoRepository.save(new Topico(datos, usuario));
    DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
        topico.getId(), 
        topico.getTitulo(), 
        topico.getMensaje(), 
        topico.getFechaCreacion(), 
        topico.getUsuario().getNombre(), 
        topico.getCurso()
    );

    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity.created(url).body(datosRespuestaTopico);
}

}
