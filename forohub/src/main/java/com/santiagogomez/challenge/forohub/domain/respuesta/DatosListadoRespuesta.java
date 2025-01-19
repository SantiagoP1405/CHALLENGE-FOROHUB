package com.santiagogomez.challenge.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(
    Long id,
    String mensaje,
    LocalDateTime fechaCreacion,
    String creadoPor
) {

    public DatosListadoRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getUsuario().getNombre());
    }
}
