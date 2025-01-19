package com.santiagogomez.challenge.forohub.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.santiagogomez.challenge.forohub.domain.respuesta.DatosActualizarRespuesta;
import com.santiagogomez.challenge.forohub.domain.respuesta.DatosRegistroRespuesta;
import com.santiagogomez.challenge.forohub.domain.respuesta.DatosRespuestaRespuesta;
import com.santiagogomez.challenge.forohub.domain.respuesta.Respuesta;
import com.santiagogomez.challenge.forohub.domain.respuesta.RespuestaRepository;
import com.santiagogomez.challenge.forohub.domain.topico.DatosActualizarTopico;
import com.santiagogomez.challenge.forohub.domain.topico.Topico;
import com.santiagogomez.challenge.forohub.domain.topico.TopicoRepository;
import com.santiagogomez.challenge.forohub.domain.usuario.Usuario;
import com.santiagogomez.challenge.forohub.domain.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public  ResponseEntity<?> crearRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.findById(datos.idUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + datos.idUsuario()));
        Topico topico = topicoRepository.findById(datos.idTopico()).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + datos.idUsuario()));
        var usuarioPassword = usuario.getPassword();
        if (passwordEncoder.matches(datos.password(), usuarioPassword)){
            Respuesta respuesta = respuestaRepository.save(new Respuesta(datos, topico, usuario));
            DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getUsuario().getNombre()
            );

             URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.getId()).toUri();
            return ResponseEntity.created(url).body(datosRespuestaRespuesta);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta. No tiene autorización para agregar una respuesta a este tópico");
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        var respuestaEncontrada = respuestaRepository.findById(datosActualizarRespuesta.id());
        if(respuestaEncontrada.isPresent()){
            Respuesta respuesta = respuestaEncontrada.get();
            respuesta.actualizarDatos(datosActualizarRespuesta);
            if (datosActualizarRespuesta.usuario() == null || datosActualizarRespuesta.password() == null ||
            !datosActualizarRespuesta.usuario().equals(respuesta.getUsuario().getNombre()) ||
            !passwordEncoder.matches(datosActualizarRespuesta.password(), respuesta.getUsuario().getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre de usuario o contraseña incorrectos. No tiene autorización para responder a este tópico");
            }
            return ResponseEntity.ok().body(new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getUsuario().getNombre()
            ));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity eliminarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {
        var id = datosActualizarRespuesta.id();
        var respuestaEncontrada = respuestaRepository.findById(id);
        if (respuestaEncontrada.isPresent()) {
            Respuesta respuesta = respuestaRepository.getReferenceById(id);
            if (datosActualizarRespuesta.usuario() == null || datosActualizarRespuesta.password() == null ||
            !datosActualizarRespuesta.usuario().equals(respuesta.getUsuario().getNombre()) ||
            !passwordEncoder.matches(datosActualizarRespuesta.password(), respuesta.getUsuario().getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre de usuario o contraseña incorrectos. No tiene autorización para eliminar esta respuesta");
            }
            respuesta.deactivarRespuesta();
            return ResponseEntity.ok("Respuesta eliminada");
        }
        return ResponseEntity.notFound().build();   
    }
}
