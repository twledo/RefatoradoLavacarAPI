package dev.ApiLavacar.Nego.mapper;

import dev.ApiLavacar.Nego.dto.JobWashDTO;
import dev.ApiLavacar.Nego.dto.ScheduleDTO;
import dev.ApiLavacar.Nego.model.JobWash;
import dev.ApiLavacar.Nego.model.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

  public Schedule toEntity(ScheduleDTO dto) {
    if (dto == null) return null;

    Schedule schedule = new Schedule();
    schedule.setNameClient(dto.getNameClient());
    schedule.setPhone(dto.getPhone());
    schedule.setModelCar(dto.getModelCar());
    schedule.setDescriptionService(dto.getDescriptionService());
    schedule.setHour(dto.getHour()); // será sobrescrito em addScheduleWash
    return schedule;
  }

  public ScheduleDTO toDto(Schedule schedule) {
    if (schedule == null) return null;

    ScheduleDTO dto = new ScheduleDTO();
    dto.setNameClient(schedule.getNameClient());
    dto.setPhone(schedule.getPhone());
    dto.setModelCar(schedule.getModelCar());
    dto.setDescriptionService(schedule.getDescriptionService());
    dto.setHour(schedule.getHour());
    dto.setActive(schedule.isActive());

    if (schedule.getJobWash() != null) {
      JobWash job = schedule.getJobWash();
      JobWashDTO jobDto = new JobWashDTO();
      jobDto.setId(job.getId());
      jobDto.setName(job.getName());
      dto.setJobWash(jobDto);
    }

    return dto;
  }
}
