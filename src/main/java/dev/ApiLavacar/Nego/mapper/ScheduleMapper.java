package dev.ApiLavacar.Nego.mapper;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.dto.JobWashDTO;
import dev.ApiLavacar.Nego.dto.ScheduleDTO;
import dev.ApiLavacar.Nego.model.Hour;
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
    schedule.setDate(dto.getDate());
    return schedule;
  }

  public ScheduleDTO toDto(Schedule schedule) {
    if (schedule == null) return null;

    ScheduleDTO dto = new ScheduleDTO();
    dto.setNameClient(schedule.getNameClient());
    dto.setPhone(schedule.getPhone());
    dto.setModelCar(schedule.getModelCar());
    dto.setDescriptionService(schedule.getDescriptionService());
    dto.setDate(schedule.getDate());
    dto.setActive(schedule.isActive());

    if (schedule.getJobWash() != null) {
      JobWash job = schedule.getJobWash();
      JobWashDTO jobDto = new JobWashDTO();
      jobDto.setId(job.getId());
      jobDto.setName(job.getName());
      dto.setJobWash(jobDto);
    }

    if (schedule.getHour() != null) {
      Hour hour = schedule.getHour();
      HourDTO hourDTO = new HourDTO();
      hourDTO.setId(hour.getId());
      hourDTO.setHour(hour.getHour());
      dto.setHourDTO(hourDTO);
    }

    return dto;
  }

}
