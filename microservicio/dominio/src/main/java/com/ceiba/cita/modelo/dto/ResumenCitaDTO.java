package com.ceiba.cita.modelo.dto;

import com.ceiba.cita.modelo.entidad.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ResumenCitaDTO {

    private Long id;
    private LocalDate fecha;
    private String jornada;
    private Double valor_copago;
    private EstadoCita estado;
}
