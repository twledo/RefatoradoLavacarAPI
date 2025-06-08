package dev.ApiLavacar.Nego.dto;

import dev.ApiLavacar.Nego.model.Hour;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
//OBJETO DE TRANSFERENCIA
public class ScheduleDTO {

        private Long id;
        private String nameClient;
        private String phone;
        private String modelCar;
        private LocalDate date;
        private Hour hour;
        private String descriptionService;
        private boolean active;
        private JobWashDTO jobWash;
}