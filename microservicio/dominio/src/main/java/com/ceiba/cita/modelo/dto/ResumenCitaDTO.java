package com.ceiba.cita.modelo.dto;

import com.ceiba.cita.modelo.entidad.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ResumenCitaDTO {

    private Long id;
    private String numeroIdentificacion;
    private String nombreAfiliado;
    private String codigoProcedimiento;
    private String nombreProcedimiento;
    private LocalDate fecha;
    private String jornada;
    private Double valorCopago;
    private EstadoCita estado;
}
