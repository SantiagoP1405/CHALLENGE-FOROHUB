package com.santiagogomez.challenge.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
    @NotBlank
    String nombre,
    @NotBlank
    String email,
    @NotBlank
    String password
) {

}
