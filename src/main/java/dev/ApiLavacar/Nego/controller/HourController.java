package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.model.Hour;
import dev.ApiLavacar.Nego.service.HourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HourController {

  @Autowired
  private HourService hourService;

  @PostMapping("/owner/addHour")
  public ResponseEntity<String> addHour(@RequestBody HourDTO hourDTO) {
    try {
      hourService.addHour(hourDTO);
      return ResponseEntity.ok("Hour added successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Error adding hour: " + e.getMessage());
    }
  }

  @PutMapping("/owner/editHours")
  public HourDTO editHour(@RequestBody HourDTO hourDTO) {
    // Aqui você deveria persistir a alteração usando o service
    return hourService.editHour(hourDTO);
  }

  @GetMapping("/public/getHours")
  public List<Hour> getHours() {
    return hourService.getAllHours();
  }
}