package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.service.HourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para gerenciar horários (Hours).
 */
@RestController
@RequestMapping("/owner")
public class HourController {

  @Autowired
  private HourService hourService;

  /**
   * Adiciona um novo horário.
   * @param hourDTO dados do horário a ser adicionado
   * @return o horário adicionado
   */
  @PostMapping("/addHour")
  public HourDTO addHour(@RequestBody HourDTO hourDTO) {
    return hourService.addHour(hourDTO);
  }

  /**
   * Edita um horário existente pelo ID.
   * @param id ID do horário a ser editado
   * @param hourDTO novos dados do horário
   * @return horário atualizado
   */
  @PutMapping("editHour/{id}")
  public HourDTO editHour(@PathVariable Long id, @RequestBody HourDTO hourDTO) {
    return hourService.editHour(id, hourDTO);
  }

  /**
   * Deleta um horário pelo ID.
   * @param id ID do horário a ser deletado
   */
  @DeleteMapping("delete/{id}")
  public void deleteHour(@PathVariable Long id) {
    hourService.deleteHour(id);
  }
}