package dev.ApiLavacar.Nego.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "hour")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hour {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalTime hour;

}
