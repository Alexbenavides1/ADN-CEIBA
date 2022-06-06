package com.ceiba.cita.comando;

import com.ceiba.cita.modelo.entidad.JornadaCita;
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

    private String identificacionAfiliado;
    private String codigoProcedimiento;
    private LocalDate fecha;
    private String jornadaCita;

}

