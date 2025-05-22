package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.dto.ScheduleDTO;
import dev.ApiLavacar.Nego.model.JobWash;
import dev.ApiLavacar.Nego.model.Schedule;
import dev.ApiLavacar.Nego.repository.JobRepository;
import dev.ApiLavacar.Nego.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private JobRepository jobRepository;

    public Schedule addScheduleWash(ScheduleDTO dto) {
        System.out.println("Recebido ScheduleDTO: " + dto);
        if (dto.getJobWashId() == null) {
            throw new IllegalArgumentException("O serviço (JobWash) deve ser informado com ID válido.");
        }

        Long jobId = dto.getJobWashId();
        JobWash job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + jobId));

        System.out.println("JobWash encontrado: " + job);

        Schedule schedule = convertToEntity(dto);
        schedule.setJobWash(job);

        System.out.println("Schedule a salvar: " + schedule);

        Schedule saved = scheduleRepository.save(schedule);

        System.out.println("Schedule salvo: " + saved);

        return saved;
    }


    public List<ScheduleDTO> returnWashes() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Conversão DTO -> Entity
    private Schedule convertToEntity(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        schedule.setNameClient(dto.getNameClient());
        schedule.setPhone(dto.getPhone());
        schedule.setModelCar(dto.getModelCar());
        schedule.setDescriptionService(dto.getDescriptionService());
        schedule.setDateTime(dto.getDateTime());
        // JobWash será setado depois, na addScheduleWash
        return schedule;
    }

    // Conversão Entity -> DTO
    private ScheduleDTO convertToDto(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setNameClient(schedule.getNameClient());
        dto.setPhone(schedule.getPhone());
        dto.setModelCar(schedule.getModelCar());
        dto.setDescriptionService(schedule.getDescriptionService());
        dto.setDateTime(schedule.getDateTime());

        if (schedule.getJobWash() != null) {
            dto.setJobWashId(schedule.getJobWash().getId());
        }

        return dto;
    }
}
