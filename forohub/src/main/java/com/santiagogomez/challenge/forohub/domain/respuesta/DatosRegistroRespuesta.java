package com.santiagogomez.challenge.forohub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
    
    @NotNull
    Long idUsuario,
    @NotNull
    String password,
    @NotNull
    Long idTopico,
    @NotNull
    String mensaje
) {

}
