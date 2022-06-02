package com.ceiba.cita.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudAsignarCita {

    private String identificacion_afiliado;
    private String codigo_procedimiento;
    private LocalDate fecha;
    private String jornada;

}

