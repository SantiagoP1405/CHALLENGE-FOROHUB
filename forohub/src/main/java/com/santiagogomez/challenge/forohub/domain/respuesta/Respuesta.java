package com.santiagogomez.challenge.forohub.domain.respuesta;

import java.time.LocalDateTime;

import com.santiagogomez.challenge.forohub.domain.topico.DatosActualizarTopico;
import com.santiagogomez.challenge.forohub.domain.topico.Topico;
import com.santiagogomez.challenge.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) // Relación con Topico
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private String mensaje;
    private boolean status;
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY) // Relación con Usuario
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Topico topico, Usuario usuario) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.topico = topico;
        this.status = true;
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = usuario;
    }

    public void actualizarDatos(DatosActualizarRespuesta datosActualizarTopico) {

        if (datosActualizarTopico.nuevoMensaje() != null) {
            this.mensaje = datosActualizarTopico.nuevoMensaje();
        }

    }

    public void deactivarRespuesta(){
        this.status = false;
    }
}
