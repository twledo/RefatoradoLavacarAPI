package dev.ApiLavacar.Nego.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//ok
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "scheduleClients")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameClient;
    private String phone;
    private String modelCar;
    private LocalDate dateTime;
    private String descriptionService;
    private boolean active = true;

    @ManyToOne
    private JobWash jobWash;
}
