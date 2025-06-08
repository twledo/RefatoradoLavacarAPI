package dev.ApiLavacar.Nego.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobWashDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
}
