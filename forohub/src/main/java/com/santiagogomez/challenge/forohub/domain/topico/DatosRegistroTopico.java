package com.santiagogomez.challenge.forohub.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

    @NotNull
    Long idUsuario,
    @NotNull
    String password,
    @NotBlank
    String mensaje,
    @NotBlank
    String nombreCurso,
    @NotBlank
    String titulo
) {
}
