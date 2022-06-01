package com.ceiba.cita.controlador;

import com.ceiba.afiliado.comando.ComandoAfiliadoAsignarCita;
import com.ceiba.cita.comando.ComandoSolicitudAsignarCita;
import com.ceiba.procedimiento.comando.ComandoProcedimientoAsignarCita;

import java.time.LocalDate;

public class ComandoAsignarCitaTestDataBuilder {

    private ComandoAfiliadoAsignarCita comandoAfiliadoAsignarCita;
    private ComandoProcedimientoAsignarCita comandoProcedimientoAsignarCita;
    private String jornada;
    private LocalDate fecha;

    public ComandoAsignarCitaTestDataBuilder() {
    }

    public ComandoAsignarCitaTestDataBuilder crearPorDefecto(){

        this.comandoAfiliadoAsignarCita=new ComandoAfiliadoAsignarCita("1067944244","Alex B",1);
        this.comandoProcedimientoAsignarCita= new ComandoProcedimientoAsignarCita("808080","P. Estetico", 300000.0);
        this.fecha=LocalDate.parse("2022-06-01");
        this.jornada="M";
        return this;
    }

    public ComandoSolicitudAsignarCita build() {
        return new ComandoSolicitudAsignarCita(comandoAfiliadoAsignarCita,comandoProcedimientoAsignarCita,fecha,jornada);
    }
}
