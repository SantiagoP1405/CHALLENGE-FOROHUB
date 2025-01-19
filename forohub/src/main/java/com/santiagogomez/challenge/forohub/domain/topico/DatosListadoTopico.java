package com.santiagogomez.challenge.forohub.domain.topico;

import java.time.LocalDateTime;
import java.util.List;

import com.santiagogomez.challenge.forohub.domain.respuesta.DatosListadoRespuesta;

public record DatosListadoTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    String creadoPor,
    String curso,
    List<DatosListadoRespuesta> respuestas
) {
  
    public DatosListadoTopico(Topico topico) {
        this(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getUsuario().getNombre(),
            topico.getCurso(),
            topico.getRespuestas().stream().map(DatosListadoRespuesta::new).toList());

    }
}
