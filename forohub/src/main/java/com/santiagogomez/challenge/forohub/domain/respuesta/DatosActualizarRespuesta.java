package com.santiagogomez.challenge.forohub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
    @NotNull
    Long id,
    String nuevoMensaje,
    @NotNull
    String usuario,
    @NotNull
    String password
) {
    
}
