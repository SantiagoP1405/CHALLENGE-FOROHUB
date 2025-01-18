package com.santiagogomez.challenge.forohub.infra;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.santiagogomez.challenge.forohub.domain.ValidacionExcepcion;

import jakarta.persistence.*;

@RestControllerAdvice //Actúa como proxy para interceptar llamadas en caso de que suceda una excepción 
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class) //Se encarga de manejar las excepciones
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream()
            .map(DatosErrorValidacion::new).toList();

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionExcepcion.class) 
    public ResponseEntity tratarErrorDeValidacion(ValidacionExcepcion e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DatosErrorValidacion(String campo, String mensaje){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}