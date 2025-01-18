package com.santiagogomez.challenge.forohub.domain.topico;

import java.time.*;

public record DatosRespuestaTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    String nombre,
    String curso
) {
    
}
