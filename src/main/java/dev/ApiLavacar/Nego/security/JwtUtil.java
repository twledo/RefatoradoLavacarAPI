package dev.ApiLavacar.Nego.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "KeySecretaParaAPIdoLavaCarNegoDaianeThiagoKeySecretaKeySecretaKeySecreta";

    /**
     * Gera um token JWT com o username como assunto (subject)
     * O token expira em 2 horas
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // informa quem é o dono do token
                .setIssuedAt(new Date()) // data da emissão
                .setExpiration(Date.from(LocalDateTime.now().plusHours(2) // data de expiração
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // algoritmo e chave para assinar o token
                .compact(); // gera o token final em String
    }

    /**
     * Extrai o nome de usuário (subject) do token JWT.
     *
     * @param token token JWT
     * @return username extraído do token
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder() // Cria o parser do JWT
                .setSigningKey(SECRET_KEY) // Define a chave secreta para validar a assinatura
                .build() // Constroi o parser configurado
                .parseClaimsJws(token) // Analisa o token e obtém os claims
                .getBody() // Corpo do token (claims)
                .getSubject(); // Retorna o assunto (username)
    }

    /**
     * Valida se o token é válido para o usuário informado.
     * Verifica se o username bate e se o token não está expirado.
     *
     * @param token       token JWT
     * @param userDetails detalhes do usuário para comparar
     * @return true se válido, false caso contrário
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Verifica se o token JWT está expirado.
     *
     * @param token token JWT
     * @return true se expirado, false caso contrário
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrai a data de expiração do token JWT.
     *
     * @param token token JWT
     * @return data de expiração
     */
    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}