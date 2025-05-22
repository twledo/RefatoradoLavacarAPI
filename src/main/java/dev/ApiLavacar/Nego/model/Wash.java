package dev.ApiLavacar.Nego.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Wash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameClient;
    private double priceWash;
    private String modelCar;
    private LocalDate dateHour;
    private String description;

    public Wash(Long id, String nameClient, double priceWash, String modelCar, LocalDate dateHour, String description) {
        this.id = id;
        this.nameClient = nameClient;
        this.priceWash = priceWash;
        this.modelCar = modelCar;
        this.dateHour = dateHour;
        this.description = description;
    }
}
