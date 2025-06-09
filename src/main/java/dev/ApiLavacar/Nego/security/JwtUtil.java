package dev.ApiLavacar.Nego.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Utilitário para criação, extração e validação de tokens JWT.
 */
@Component
public class JwtUtil {

    /**
     * Chave secreta usada para assinar e validar tokens JWT.
     */
    private final String SECRET_KEY = "KeySecretaParaAPIdoLavaCarNegoDaianeThiagoKeySecretaKeySecretaKeySecreta";

    /**
     * Gera um token JWT com o username como assunto (subject).
     * O token expira em 2 horas a partir do momento da emissão.
     *
     * @param username nome do usuário para incluir no token
     * @return token JWT gerado como String
     */
    public String generateToken(String username) {
        return Jwts.builder()
          .setSubject(username)
          .setIssuedAt(new Date())
          .setExpiration(Date.from(LocalDateTime.now().plusHours(2)
            .atZone(ZoneId.systemDefault()).toInstant()))
          .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
          .compact();
    }

    /**
     * Extrai o nome de usuário (subject) do token JWT.
     *
     * @param token token JWT
     * @return username extraído do token
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
          .setSigningKey(SECRET_KEY)
          .build()
          .parseClaimsJws(token)
          .getBody()
          .getSubject();
    }

    /**
     * Valida se o token é válido para o usuário informado.
     * Verifica se o username corresponde ao do token e se o token não está expirado.
     *
     * @param token       token JWT a ser validado
     * @param userDetails detalhes do usuário para comparação
     * @return true se o token é válido, false caso contrário
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Verifica se o token JWT está expirado.
     *
     * @param token token JWT
     * @return true se o token está expirado, false caso contrário
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrai a data de expiração do token JWT.
     *
     * @param token token JWT
     * @return data de expiração do token
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