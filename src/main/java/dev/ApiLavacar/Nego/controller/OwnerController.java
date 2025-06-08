package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.dto.LoginRequestDTO;
import dev.ApiLavacar.Nego.dto.ScheduleDTO;
import dev.ApiLavacar.Nego.security.JwtUtil;
import dev.ApiLavacar.Nego.service.HourService;
import dev.ApiLavacar.Nego.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private HourService hourService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint para login de usuário.
     * Recebe username e senha, valida, e retorna token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
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

    @GetMapping("/getWashes")
    public ResponseEntity<?> getAllWashes() {
        try {
            List<ScheduleDTO> washes = scheduleService.returnWashes();
            if (washes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum agendamento encontrado.");
            }
            return ResponseEntity.ok(washes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar agendamentos: " + e.getMessage());
        }
    }
}
