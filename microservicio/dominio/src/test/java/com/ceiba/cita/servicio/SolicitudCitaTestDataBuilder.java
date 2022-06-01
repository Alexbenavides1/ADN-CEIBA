package com.ceiba.cita.servicio;

import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.cita.modelo.entidad.SolicitudAsignarCita;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;

import java.time.LocalDate;

public class SolicitudCitaTestDataBuilder {

    private Afiliado afiliado;
    private Procedimiento procedimiento;
    private LocalDate fecha;
    private String jornada;

    public SolicitudCitaTestDataBuilder() {
    }

    public SolicitudCitaTestDataBuilder conAfiliado(Afiliado afiliado){
        this.afiliado=afiliado;
        return  this;
    }

    public SolicitudCitaTestDataBuilder conProcedimiento(Procedimiento procedimiento){
        this.procedimiento=procedimiento;
        return this;
    }

    public SolicitudCitaTestDataBuilder conFecha(LocalDate fecha){
        this.fecha=fecha;
        return  this;
    }

    public SolicitudCitaTestDataBuilder conJornada(String jornada){
        this.jornada=jornada;
        return this;
    }

    public SolicitudAsignarCita build(){
        return  new SolicitudAsignarCita(afiliado,procedimiento,fecha,jornada);
    }

}
