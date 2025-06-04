package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.model.Hour;
import dev.ApiLavacar.Nego.repository.HoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HourService {

  @Autowired
  private HoursRepository hoursRepository;

  public HourDTO addHour(HourDTO hourDTO) {
    Hour hour = new Hour();
    hour.setHour(hourDTO.getHour());

    Hour saved = hoursRepository.save(hour);

    HourDTO response = new HourDTO();
    response.setHour(saved.getHour());

    return response;
  }

  public List<Hour> getAllHours() {
    return hoursRepository.findAll();
  }

  public HourDTO editHour(HourDTO hourDTO) {
    // Busca a entidade no banco pelo ID
    Optional<Hour> optionalHour = hoursRepository.findById(hourDTO.getId());

    if (optionalHour.isPresent()) {
      Hour hour = optionalHour.get();
      // Atualiza os campos
      hour.setHour(hourDTO.getHour());

      // Salva no banco
      Hour updated = hoursRepository.save(hour);

      // Prepara e retorna o DTO de resposta
      HourDTO response = new HourDTO();
      response.setId(updated.getId());
      response.setHour(updated.getHour());

      return response;
    } else {
      throw new RuntimeException("Hour not found with id: " + hourDTO.getId());
    }
  }

}
