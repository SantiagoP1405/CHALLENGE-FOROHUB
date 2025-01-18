package com.santiagogomez.challenge.forohub.domain.topico;

import java.time.*;

import com.santiagogomez.challenge.forohub.domain.usuario.Usuario;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true)
    private String titulo;
    @Column (unique = true)
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private boolean status;
    @ManyToOne(fetch = FetchType.LAZY) // Relación con Usuario
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String curso;
    

    public Topico(DatosRegistroTopico datosRegistro, Usuario usuario) {
        this.titulo = datosRegistro.titulo();
        this.mensaje = datosRegistro.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.usuario = usuario;
        this.curso = datosRegistro.nombreCurso();
    }

    public void desactivarTopico(){
        this.status = false;
    }


}
