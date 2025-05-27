package dev.ApiLavacar.Nego.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleDTO {
        private String nameClient;
        private String phone;
        private String modelCar;
        private LocalDateTime dateTime;
        private String descriptionService;
        private boolean active;
        private JobWashDTO jobWash;
}
