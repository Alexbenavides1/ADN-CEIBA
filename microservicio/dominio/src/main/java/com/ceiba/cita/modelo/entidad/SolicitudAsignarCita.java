package com.ceiba.cita.modelo.entidad;

import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class SolicitudAsignarCita {

    private Afiliado afiliado;
    private Procedimiento procedimiento;
    private LocalDate fecha;
    private JornadaCita jornadaCita;
}
