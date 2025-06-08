package dev.ApiLavacar.Nego.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HourDTO {

  private Long id;

  @JsonFormat(pattern = "HH:mm")
  private LocalTime hour;

}