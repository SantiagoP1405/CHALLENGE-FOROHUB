package com.santiagogomez.challenge.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(

    @NotBlank
    Long idUsuario,
    @NotBlank
    String mensaje,
    @NotBlank
    String nombreCurso,
    @NotBlank
    String titulo
) {
}
