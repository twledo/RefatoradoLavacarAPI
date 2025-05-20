package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.model.Wash;
import dev.ApiLavacar.Nego.repository.WashRepository;
import dev.ApiLavacar.Nego.service.WashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wash")
public class WashController {

    @Autowired
    private WashService washService;

    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleWash(@RequestBody Wash wash) {
        try {
            Wash newWash = washService.scheduleWash(wash);
            return ResponseEntity.ok(newWash);
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Erro ao agendar lavagem: " + e.getMessage());
        }
    }
}
