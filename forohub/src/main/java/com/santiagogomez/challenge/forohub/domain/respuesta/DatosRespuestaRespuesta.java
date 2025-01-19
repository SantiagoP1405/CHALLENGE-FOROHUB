package com.santiagogomez.challenge.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(
    Long id,
    String mensaje,
    LocalDateTime fechaCreacion,
    String creadoPor
) {
    
}
