package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.model.LoginRequest;
import dev.ApiLavacar.Nego.model.Wash;
import dev.ApiLavacar.Nego.security.JwtUtil;
import dev.ApiLavacar.Nego.service.WashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private WashService washService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint para login de usuário.
     * Recebe username e senha, valida, e retorna token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autentica o usuário com os dados fornecidos
            var authToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword());

            authenticationManager.authenticate(authToken);

            // Gera o token JWT
            String token = jwtUtil.generateToken(loginRequest.getUsername());

            // Retorna o token
            return ResponseEntity.ok(Map.of("token", token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(403).body("Usuário ou senha inválidos.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar o login." + e.getMessage());
        }
    }

    @GetMapping("/washes")
    public ResponseEntity<?> getAllWash() {
        try {
            List<Wash> washes = washService.getAllWashes();
            return ResponseEntity.ok(washes);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body("Acesso negado. Você não tem permissão para acessar este recurso.");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Não autenticado. Faça login para acessar este recurso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Acesso negado.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWash(@PathVariable Long id) {
        try {
            washService.delete(id);
            return ResponseEntity.ok("Lavagem deletada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Lavagem não encontrada");
        }
    }

    @DeleteMapping("/deleteAllWashes")
    public ResponseEntity<?> deleteWash() {
        try {
            washService.deleteAllWashes();
            return ResponseEntity.ok("Lavagens deletadas com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Sem autorização");
        }
    }
}
