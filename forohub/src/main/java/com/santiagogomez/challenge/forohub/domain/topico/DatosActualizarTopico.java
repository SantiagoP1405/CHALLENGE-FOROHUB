package com.santiagogomez.challenge.forohub.domain.topico;

import com.santiagogomez.challenge.forohub.domain.usuario.Usuario;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
    @NotNull
    Long id,
    String nuevoTitulo,
    String nuevoMensaje,
    String nuevoCurso,
    @NotNull
    String usuario,
    @NotNull
    String password
) {


} 