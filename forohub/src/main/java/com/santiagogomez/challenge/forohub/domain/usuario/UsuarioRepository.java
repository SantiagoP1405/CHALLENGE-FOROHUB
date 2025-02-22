package com.santiagogomez.challenge.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);

    
}
