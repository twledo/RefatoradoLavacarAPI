package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.model.Hour;
import dev.ApiLavacar.Nego.repository.HoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HourService {

  @Autowired
  private HoursRepository hoursRepository;

  public HourDTO addHour(HourDTO hourDTO) {
    Hour hour = new Hour();
    hour.setHour(hourDTO.getHour());
    Hour saved = hoursRepository.save(hour);
    return new HourDTO(saved.getId(), saved.getHour());
  }

  public List<HourDTO> getAllHours() {
    return hoursRepository.findAll()
      .stream()
      .map(h -> new HourDTO(h.getId(), h.getHour()))
      .collect(Collectors.toList());
  }

  public HourDTO editHour(Long id, HourDTO hourDTO) {
    Optional<Hour> optionalHour = hoursRepository.findById(id);
    if (optionalHour.isPresent()) {
      Hour hour = optionalHour.get();
      hour.setHour(hourDTO.getHour());
      Hour updated = hoursRepository.save(hour);
      return new HourDTO(updated.getId(), updated.getHour());
    }
    return null;
  }

  public HourDTO findById(Long id) {
    return hoursRepository.findById(id)
      .map(hour -> new HourDTO(hour.getId(), hour.getHour()))
      .orElse(null); // ou lançar uma exceção, se preferir
  }

  public void deleteHour(Long id) {
    hoursRepository.deleteById(id);
  }
}
