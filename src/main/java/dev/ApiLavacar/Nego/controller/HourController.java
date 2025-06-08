package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.service.HourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class HourController {

  @Autowired
  private HourService hourService;

  @PostMapping("/addHour")
  public HourDTO addHour(@RequestBody HourDTO hourDTO) {
    return hourService.addHour(hourDTO);
  }

  @PutMapping("editHour/{id}")
  public HourDTO editHour(@PathVariable Long id, @RequestBody HourDTO hourDTO) {
    return hourService.editHour(id, hourDTO);
  }

  @DeleteMapping("delete/{id}")
  public void deleteHour(@PathVariable Long id) {
    hourService.deleteHour(id);
  }
}
