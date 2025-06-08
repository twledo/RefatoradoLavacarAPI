package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.dto.JobWashDTO;
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
        if (dto.getHour() == null || dto.getHour().getId() == null)
            throw new RuntimeException("Hora não informada no DTO");

        if (dto.getJobWash() == null || dto.getJobWash().getId() == null)
            throw new RuntimeException("Serviço não informado no DTO");

        Long idHour = dto.getHour().getId();
        Hour hour = hoursRepository.findById(idHour)
          .orElseThrow(() -> new RuntimeException("Hora não encontrada com ID: \"" + idHour + "\""));
        System.out.println("Hora encontrada: " + hour.getHour());

        Long idJob = dto.getJobWash().getId();
        JobWash job = jobRepository.findById(idJob)
          .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: \"" + idJob + "\""));
        System.out.println("Serviço encontrado: " + job.getName());

        Schedule entitySchedule = scheduleMapper.toEntity(dto);
        entitySchedule.setHour(hour);
        entitySchedule.setJobWash(job);

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
