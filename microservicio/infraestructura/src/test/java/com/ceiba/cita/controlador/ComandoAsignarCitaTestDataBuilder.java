package com.ceiba.cita.controlador;

import com.ceiba.cita.comando.ComandoSolicitudAsignarCita;

import java.time.LocalDate;

public class ComandoAsignarCitaTestDataBuilder {

    private String identificacion_afiliado;
    private String codigo_prodedimiento;
    private String jornada;
    private LocalDate fecha;

    public ComandoAsignarCitaTestDataBuilder() {
    }

    public ComandoAsignarCitaTestDataBuilder crearPorDefecto(){

        this.identificacion_afiliado="1067555555";
        this.codigo_prodedimiento="808081";
        this.fecha=LocalDate.parse("2022-06-01");
        this.jornada="M";
        return this;
    }

    public ComandoSolicitudAsignarCita build() {
        return new ComandoSolicitudAsignarCita(identificacion_afiliado,codigo_prodedimiento,fecha,jornada);
    }
}
