package com.santiagogomez.challenge.forohub.domain.topico;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagogomez.challenge.forohub.domain.usuario.Usuario;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByStatusTrue(Pageable paginacion);

    Page<Topico> findByStatusFalse(Pageable paginacion);

    
} 