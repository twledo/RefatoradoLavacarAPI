package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.model.Hour;
import dev.ApiLavacar.Nego.repository.HoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelas operações relacionadas às horas (Hour).
 */
@Service
public class HourService {

  @Autowired
  private HoursRepository hoursRepository;

  /**
   * Adiciona uma nova hora ao repositório.
   *
   * @param hourDTO objeto com dados da hora a ser adicionada
   * @return o objeto HourDTO com os dados salvos, incluindo o ID gerado
   */
  public HourDTO addHour(HourDTO hourDTO) {
    Hour hour = new Hour();
    hour.setHour(hourDTO.getHour());
    Hour saved = hoursRepository.save(hour);
    return new HourDTO(saved.getId(), saved.getHour());
  }

  /**
   * Retorna todas as horas cadastradas no sistema.
   *
   * @return lista de objetos HourDTO representando todas as horas
   */
  public List<HourDTO> getAllHours() {
    return hoursRepository.findAll()
      .stream()
      .map(h -> new HourDTO(h.getId(), h.getHour()))
      .collect(Collectors.toList());
  }

  /**
   * Edita uma hora existente com base no ID informado.
   *
   * @param id o identificador da hora a ser editada
   * @param hourDTO objeto com os novos dados da hora
   * @return o objeto HourDTO atualizado ou null caso o ID não seja encontrado
   */
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

  /**
   * Busca uma hora pelo seu ID.
   *
   * @param id o identificador da hora
   * @return o objeto HourDTO correspondente ou null caso não encontrado
   */
  public HourDTO findById(Long id) {
    return hoursRepository.findById(id)
      .map(hour -> new HourDTO(hour.getId(), hour.getHour()))
      .orElse(null);
  }

  /**
   * Remove uma hora do sistema pelo seu ID.
   *
   * @param id o identificador da hora a ser removida
   */
  public void deleteHour(Long id) {
    hoursRepository.deleteById(id);
  }
}
