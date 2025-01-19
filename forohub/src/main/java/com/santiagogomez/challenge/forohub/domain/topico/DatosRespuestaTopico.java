package com.santiagogomez.challenge.forohub.domain.topico;

import java.time.*;
import java.util.List;

import com.santiagogomez.challenge.forohub.domain.respuesta.DatosListadoRespuesta;

public record DatosRespuestaTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    String creadoPor,
    String curso,
    List<DatosListadoRespuesta> respuestas
) {

    public DatosRespuestaTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String creadoPor,
            String curso, List<DatosListadoRespuesta> respuestas) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.creadoPor = creadoPor;
        this.curso = curso;
        this.respuestas = respuestas;
    }
 
}
