package com.example.pruebaTecnicaSpringBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class VueloDto {

    private int id;
    private String nombreVuelo;
    private String empresa;
    private String lugarSalida;
    private String lugarLlegada;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private Long duracionVuelo;
}
