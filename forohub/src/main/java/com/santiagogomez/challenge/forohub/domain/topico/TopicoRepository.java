package com.santiagogomez.challenge.forohub.domain.topico;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.santiagogomez.challenge.forohub.domain.usuario.Usuario;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByStatusTrue(Pageable paginacion);

    Page<Topico> findByStatusFalse(Pageable paginacion);

    @Query(value = "SELECT * FROM topicos t LEFT JOIN respuestas r ON t.id = r.topico_id WHERE t.id = :id", nativeQuery = true)
    Optional<Topico> findByIdWithRespuestasNative(@Param("id") Long id);


} 