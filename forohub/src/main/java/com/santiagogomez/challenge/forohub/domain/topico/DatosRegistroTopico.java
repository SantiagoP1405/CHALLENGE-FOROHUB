package com.santiagogomez.challenge.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

    @NotNull
    Long idUsuario,
    @NotBlank
    String mensaje,
    @NotBlank
    String nombreCurso,
    @NotBlank
    String titulo
) {
}
