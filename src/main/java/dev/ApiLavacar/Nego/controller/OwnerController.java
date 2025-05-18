package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.model.Owner;
import dev.ApiLavacar.Nego.model.Wash;
import dev.ApiLavacar.Nego.service.OwnerService;
import dev.ApiLavacar.Nego.service.WashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private WashService washService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Owner owner) {
        Optional<Owner> ownerAuth = ownerService.authenticateOwner(owner.getUsername(), owner.getPassword());
        if (ownerAuth.isPresent()) {
            return ResponseEntity.ok("/owner/panelAdm");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }

    @GetMapping("/washes")
    public List<Wash> getAllWashes() {
        return washService.getAllWashes();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {washService.delete(id);}
}
