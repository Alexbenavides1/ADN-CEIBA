package com.ceiba.cita.comando;

import com.ceiba.afiliado.comando.ComandoAfiliadoAsignarCita;
import com.ceiba.procedimiento.comando.ComandoProcedimientoAsignarCita;
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

    private ComandoAfiliadoAsignarCita comandoAfiliadoAsignarCita;
    private ComandoProcedimientoAsignarCita comandoProcedimientoAsignarCita;
    private LocalDate fecha;
    private String jornada;

}

