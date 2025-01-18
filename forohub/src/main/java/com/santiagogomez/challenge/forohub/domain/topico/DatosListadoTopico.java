package com.santiagogomez.challenge.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    String creadoPor,
    String curso
) {
  
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getUsuario().getNombre(), topico.getCurso());
    }
}
