package com.santiagogomez.challenge.forohub.controller;

import java.net.*;


import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.santiagogomez.challenge.forohub.domain.topico.*;
import com.santiagogomez.challenge.forohub.domain.usuario.*;

import jakarta.transaction.Transactional;
import jakarta.validation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoControler {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //AÑADIR TOPICO
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

    //LISTAR TÓPICOS SIN RESPONDER
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> obtenerTopicosSinResolver(@PageableDefault Pageable paginacion) {
        Page<Topico> topicos = topicoRepository.findByStatusTrue(paginacion);
        Page<DatosListadoTopico> datosListadoTopicos = topicos.map(DatosListadoTopico::new);
        return ResponseEntity.ok(datosListadoTopicos);
    }



    //BUSCAR POR IR
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> buscarTopicoPorId(@PathVariable Long id) {
        var topicoEncontrado = topicoRepository.findById(id);
        if (topicoEncontrado.isPresent()){
            Topico topico = topicoEncontrado.get();
            var datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                    topico.getFechaCreacion(), topico.getUsuario().getNombre(), topico.getCurso());
            return ResponseEntity.ok(datosRespuestaTopico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //ACTUALIZAR TITULO-MENSAJE-CURSO
    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var topicoEncontrado = topicoRepository.findById(datosActualizarTopico.id());
        if (topicoEncontrado.isPresent()){
            Topico topico = topicoEncontrado.get();
            topico.actualizarDatos(datosActualizarTopico);
            if (datosActualizarTopico.usuario() == null || datosActualizarTopico.password() == null ||
                !datosActualizarTopico.usuario().equals(topico.getUsuario().getNombre()) ||
                !datosActualizarTopico.password().equals(topico.getUsuario().getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre de usuario o contraseña incorrectos. No tiene autorización para actualizar el tópico");
            }
            return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getUsuario().getNombre(), topico.getCurso()));
        } 
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity eliminarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var id = datosActualizarTopico.id();
        var topicoEncontrado = topicoRepository.findById(id);
        if (topicoEncontrado.isPresent()) {
            Topico topico = topicoRepository.getReferenceById(id);
            if (datosActualizarTopico.usuario() == null || datosActualizarTopico.password() == null ||
            !datosActualizarTopico.usuario().equals(topico.getUsuario().getNombre()) ||
            !passwordEncoder.matches(datosActualizarTopico.password(), topico.getUsuario().getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre de usuario o contraseña incorrectos. No tiene autorización para eliminar el tópico");
            }
            topico.desactivarTopico();
            return ResponseEntity.ok("Topico eliminado");
        }
        return ResponseEntity.notFound().build();   
    }

}
