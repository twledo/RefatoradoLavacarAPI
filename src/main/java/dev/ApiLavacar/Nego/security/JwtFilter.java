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

    /**
     * Utilitário para operações com JWT, como geração e validação de tokens.
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Serviço customizado que carrega detalhes do proprietário para autenticação.
     */
    @Autowired
    private OwnerDetailsService ownerDetailsService;

    /**
     * Método principal do filtro que verifica o token JWT em cada requisição.
     * Caso o token seja válido, a autenticação é configurada no contexto de segurança do Spring.
     *
     * @param request  requisição HTTP atual
     * @param response resposta HTTP atual
     * @param filterChain cadeia de filtros para continuar a requisição
     * @throws ServletException exceção de servlet
     * @throws IOException      exceção de entrada/saída
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = ownerDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}