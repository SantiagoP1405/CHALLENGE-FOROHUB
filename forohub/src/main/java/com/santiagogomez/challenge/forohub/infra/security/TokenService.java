package com.santiagogomez.challenge.forohub.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.santiagogomez.challenge.forohub.domain.usuario.Usuario;

@Service
public class TokenService {

    @Value("$api.security.secret")
    private String apiSecret;
    
    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //ESTE TOKEN NORMALMENTE DEBE ESTAR OCULTO
            return JWT.create()
                .withIssuer("forohub")
                .withSubject(usuario.getNombre())
                .withClaim("id", usuario.getId())
                .withExpiresAt(generarFechaDeExpiracion())
                .sign(algorithm);
        } 
        catch (JWTCreationException exception){
            throw new RuntimeException("No se pudo crear el token de acceso: " + exception.getMessage(), exception);
        }
    }

    private Instant generarFechaDeExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token inválido");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                .withIssuer("forohub")
                .build()
                .verify(token);
        
            verifier.getSubject();
        } 
        catch (JWTVerificationException exception){
            // Invalid signature/claims
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier inválido");
        }
        return verifier.getSubject();
    }
}
