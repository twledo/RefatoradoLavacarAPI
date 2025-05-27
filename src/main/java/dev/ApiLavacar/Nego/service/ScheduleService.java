package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.dto.JobWashDTO;
import dev.ApiLavacar.Nego.dto.ScheduleDTO;
import dev.ApiLavacar.Nego.model.JobWash;
import dev.ApiLavacar.Nego.model.Schedule;
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
    private ScheduleRepository scheduleRepository;

    @Autowired
    private JobRepository jobRepository;

    public Schedule addScheduleWash(ScheduleDTO dto) {
        Long idJob = dto.getJobWash().getId();
        JobWash job = jobRepository.findById(idJob)
                    .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: \" + idJob"));
        System.out.println("Serviço encontrado: " + job);
        Schedule entitySchedule = convertToEntity(dto);
        entitySchedule.setJobWash(job);
        return scheduleRepository.save(entitySchedule);
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
        dto.setActive(schedule.isActive());

        if (schedule.getJobWash() != null) {
            JobWashDTO jobWashDto = new JobWashDTO();
            jobWashDto.setId(schedule.getJobWash().getId());
            jobWashDto.setName(schedule.getJobWash().getName());
            dto.setJobWash(jobWashDto);
        }

        return dto;
    }
}
