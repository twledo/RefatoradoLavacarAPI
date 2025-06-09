package dev.ApiLavacar.Nego.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "schedule")
@Getter
@Setter
//OBJETO QUE VAI PRO BANCO DE DADOS
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameClient;
    private String phone;
    private String modelCar;
    private LocalDate date;
    private String descriptionService;
    private boolean active = true;

    //Muitos agendamentos podem estar relacionados a uma única hora
    @ManyToOne
    private Hour hour;

    //Muitos agendamentos podem estar relacionados a um unico serviço
    @ManyToOne
    private JobWash jobWash;
}

