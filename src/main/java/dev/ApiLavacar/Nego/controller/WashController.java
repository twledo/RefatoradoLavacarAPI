package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.model.Wash;
import dev.ApiLavacar.Nego.repository.WashRepository;
import dev.ApiLavacar.Nego.service.WashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wash")
public class WashController {

    @Autowired
    private WashService washService;

    @PostMapping("/schedule")
    public Wash scheduleWash(@RequestBody Wash wash) {
        return washService.scheduleWash(wash);
    }
}
