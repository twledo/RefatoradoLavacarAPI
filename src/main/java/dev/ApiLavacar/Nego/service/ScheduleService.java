package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.dto.ScheduleDTO;
import dev.ApiLavacar.Nego.mapper.ScheduleMapper;
import dev.ApiLavacar.Nego.model.Hour;
import dev.ApiLavacar.Nego.model.JobWash;
import dev.ApiLavacar.Nego.model.Schedule;
import dev.ApiLavacar.Nego.repository.HoursRepository;
import dev.ApiLavacar.Nego.repository.JobRepository;
import dev.ApiLavacar.Nego.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de agendamento de lavagens.
 */
@Service
public class ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private HoursRepository hoursRepository;

    /**
     * Adiciona um novo agendamento de lavagem baseado nos dados do ScheduleDTO.
     *
     * @param dto objeto ScheduleDTO contendo os dados do agendamento, incluindo HourDTO e JobWash
     * @return o objeto Schedule persistido no banco de dados
     * @throws IllegalArgumentException se o HourDTO ou JobWash não estiverem presentes ou não forem encontrados no banco
     */
    public Schedule addScheduleWash(ScheduleDTO dto) {
        if (dto.getHourDTO() == null || dto.getHourDTO().getId() == null) {
            throw new IllegalArgumentException("Hora não informada no DTO");
        }

        if (dto.getJobWash() == null || dto.getJobWash().getId() == null) {
            throw new IllegalArgumentException("Serviço de lavagem não informado no DTO");
        }

        Optional<Hour> hourOptional = hoursRepository.findById(dto.getHourDTO().getId());
        if (hourOptional.isEmpty()) {
            throw new IllegalArgumentException("Hora com ID " + dto.getHourDTO().getId() + " não encontrada");
        }

        Optional<JobWash> jobOptional = jobRepository.findById(dto.getJobWash().getId());
        if (jobOptional.isEmpty()) {
            throw new IllegalArgumentException("Serviço com ID " + dto.getJobWash().getId() + " não encontrado");
        }

        Schedule entitySchedule = scheduleMapper.toEntity(dto);
        entitySchedule.setHour(hourOptional.get());
        entitySchedule.setJobWash(jobOptional.get());

        return scheduleRepository.save(entitySchedule);
    }

    /**
     * Remove um agendamento pelo seu ID.
     *
     * @param id o identificador do agendamento a ser removido
     */
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }

    /**
     * Retorna a lista de todos os agendamentos de lavagem cadastrados.
     *
     * @return lista de ScheduleDTO representando todos os agendamentos existentes
     */
    public List<ScheduleDTO> returnWashes() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
          .map(scheduleMapper::toDto)
          .collect(Collectors.toList());
    }
}
