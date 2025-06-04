package dev.ApiLavacar.Nego.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ScheduleDTO {
        private String nameClient;
        private String phone;
        private String modelCar;
        private LocalDate dateTime;
        private String descriptionService;
        private boolean active;
        private JobWashDTO jobWash;
}
