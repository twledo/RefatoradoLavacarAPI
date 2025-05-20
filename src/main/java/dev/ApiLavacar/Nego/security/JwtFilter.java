package dev.ApiLavacar.Nego.security;

import dev.ApiLavacar.Nego.model.OwnerDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro JWT que intercepta todas as requisições HTTP e valida o token JWT presente no cabeçalho Authorization.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OwnerDetailsService ownerDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Recupera o cabeçalho Authorization
        String authHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        // Verifica se o cabeçalho começa com "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7); // remove o prefixo "Bearer "
            username = jwtUtil.extractUsername(token); // extrai o username do token
        }

        // Se tiver username e o contexto de segurança ainda não está autenticado
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Carrega os dados do usuário
            UserDetails userDetails = ownerDetailsService.loadUserByUsername(username);

            // Valida o token
            if (jwtUtil.validateToken(token, userDetails)) {
                // Cria o token de autenticação
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Seta a autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continua a requisição
        filterChain.doFilter(request, response);
    }
}
