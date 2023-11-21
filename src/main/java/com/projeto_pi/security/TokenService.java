package com.projeto_pi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projeto_pi.models.Usuario;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Service
public class TokenService {

    private final String secret = Dotenv.load().get("JWT_SECRET");

    public String generateToken(Usuario user) {
        try {
            return JWT.create()
                    .withIssuer("ecommerce")
                    .withSubject(user.getEmail())
                    .withExpiresAt(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(2).toInstant())
                    .withClaim("privilegio", user.getPrivilegio().name())
                    .withClaim("usuarioId", user.getUsuarioId().toString())
                    .sign(Algorithm.HMAC256(Objects.requireNonNull(secret)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(Objects.requireNonNull(secret)))
                    .withIssuer("ecommerce")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
