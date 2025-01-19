CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    topico_id BIGINT NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status TINYINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_respuesta_topico_id FOREIGN KEY (topico_id) REFERENCES topicos(id) ON DELETE CASCADE,
    CONSTRAINT fk_respuesta_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);
