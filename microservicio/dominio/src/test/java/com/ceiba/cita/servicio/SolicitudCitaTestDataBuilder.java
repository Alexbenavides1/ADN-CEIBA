package com.ceiba.cita.servicio;

import com.ceiba.afiliado.AfiliadoTestDataBuilder;
import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.cita.modelo.entidad.JornadaCita;
import com.ceiba.cita.modelo.entidad.SolicitudAsignarCita;
import com.ceiba.procedimiento.ProcedimientoTestDataBuilder;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;

import java.time.LocalDate;

public class SolicitudCitaTestDataBuilder {

    private Afiliado afiliado;
    private Procedimiento procedimiento;
    private LocalDate fecha;
    private String jornadaCita;

    public SolicitudCitaTestDataBuilder() {
        this.fecha= LocalDate.parse("2022-06-10");
        this.jornadaCita="M";
        this.afiliado=new AfiliadoTestDataBuilder().conAfiliadoPorDefecto().reconstruir();
        this.procedimiento = new ProcedimientoTestDataBuilder().conProcedimientoPorDefecto().reconstruir();
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

    public SolicitudCitaTestDataBuilder conJornada(String jornadaCita){
        this.jornadaCita=jornadaCita;
        return this;
    }

    public SolicitudAsignarCita build(){
        return  new SolicitudAsignarCita(afiliado,procedimiento,fecha,jornadaCita);
    }

}
