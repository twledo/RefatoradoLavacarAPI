package dev.ApiLavacar.Nego.config;

import dev.ApiLavacar.Nego.security.JwtFilter;
import dev.ApiLavacar.Nego.model.OwnerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    /**
     * Filtro responsável por interceptar e validar tokens JWT nas requisições.
     */
    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Serviço customizado que carrega detalhes do proprietário para autenticação.
     */
    @Autowired
    private OwnerDetailsService ownerDetailsService;

    /**
     * Configura as regras de segurança da aplicação.
     *
     * Define quais endpoints são públicos, quais requerem autenticação,
     * desabilita CSRF, habilita CORS, configura o gerenciamento de sessão como stateless,
     * configura o provedor de autenticação e adiciona o filtro JWT antes do filtro padrão de autenticação.
     *
     * @param http objeto HttpSecurity para configurar as regras de segurança
     * @return SecurityFilterChain configurada com as regras definidas
     * @throws Exception exceções que podem ocorrer durante a configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .cors(cors -> corsConfigurationSource())
          .csrf(csrf -> csrf.disable()) // Desabilita CSRF para API REST
          .authorizeHttpRequests(auth -> auth
            .requestMatchers("/owner/login").permitAll() // Permite acesso livre ao login
            .requestMatchers("/wash/schedule").permitAll() // Permite acesso livre ao agendamento
            .requestMatchers("/owner/**").authenticated() // Requer autenticação para endpoints /owner/**
            .anyRequest().permitAll() // Permite acesso livre para demais endpoints
          )
          .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura sessão stateless
          .authenticationProvider(authenticationProvider()) // Define o provedor de autenticação customizado
          .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Adiciona filtro JWT antes do filtro padrão

        return http.build();
    }

    /**
     * Configura a política de CORS (Cross-Origin Resource Sharing) para a aplicação.
     *
     * Define quais origens, métodos HTTP e cabeçalhos são permitidos,
     * além de permitir o envio de credenciais (cookies, autenticação HTTP).
     *
     * @return CorsConfigurationSource fonte da configuração CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://127.0.0.1:5500");
        configuration.addAllowedOrigin("http://localhost:5500");
        configuration.addAllowedMethod("*"); // Permite todos os métodos HTTP
        configuration.addAllowedHeader("*"); // Permite todos os cabeçalhos
        configuration.setAllowCredentials(true); // Permite envio de credenciais
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Define o provedor de autenticação baseado em DaoAuthenticationProvider,
     * que utiliza o serviço customizado OwnerDetailsService para carregar dados do usuário
     * e o PasswordEncoder para validar a senha.
     *
     * @return AuthenticationProvider configurado para autenticação da aplicação
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(ownerDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Gerenciador de autenticação da aplicação,
     * utilizado para autenticar usuários a partir do AuthenticationConfiguration.
     *
     * @param config configuração de autenticação do Spring Security
     * @return AuthenticationManager para realizar autenticações
     * @throws Exception se ocorrer erro ao obter o AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Bean responsável por codificar as senhas utilizando o algoritmo BCrypt,
     * garantindo segurança no armazenamento das senhas.
     *
     * @return PasswordEncoder para codificação de senhas
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
