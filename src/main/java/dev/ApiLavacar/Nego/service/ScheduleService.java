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

    public Schedule addScheduleWash(ScheduleDTO dto) {
        // Validar se hourDTO e jobWash existem no DTO
        if (dto.getHourDTO() == null || dto.getHourDTO().getId() == null) {
            throw new IllegalArgumentException("Hora não informada no DTO");
        }

        if (dto.getJobWash() == null || dto.getJobWash().getId() == null) {
            throw new IllegalArgumentException("Serviço de lavagem não informado no DTO");
        }

        // Buscar Hour pelo ID
        Optional<Hour> hourOptional = hoursRepository.findById(dto.getHourDTO().getId());
        if (hourOptional.isEmpty()) {
            throw new IllegalArgumentException("Hora com ID " + dto.getHourDTO().getId() + " não encontrada");
        }

        // Buscar JobWash pelo ID
        Optional<JobWash> jobOptional = jobRepository.findById(dto.getJobWash().getId());
        if (jobOptional.isEmpty()) {
            throw new IllegalArgumentException("Serviço com ID " + dto.getJobWash().getId() + " não encontrado");
        }

        // Converter DTO para entidade
        Schedule entitySchedule = scheduleMapper.toEntity(dto);

        // Setar Hour e JobWash na entidade
        entitySchedule.setHour(hourOptional.get());
        entitySchedule.setJobWash(jobOptional.get());

        // Salvar no banco
        return scheduleRepository.save(entitySchedule);
    }

    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<ScheduleDTO> returnWashes() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
          .map(scheduleMapper::toDto)
          .collect(Collectors.toList());
    }
}
