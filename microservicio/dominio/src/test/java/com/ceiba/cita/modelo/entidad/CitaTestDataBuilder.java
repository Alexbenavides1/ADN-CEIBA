package com.ceiba.cita.modelo.entidad;

import com.ceiba.afiliado.AfiliadoTestDataBuilder;
import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.cita.servicio.SolicitudCitaTestDataBuilder;
import com.ceiba.procedimiento.ProcedimientoTestDataBuilder;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;

import java.time.LocalDate;

public class CitaTestDataBuilder {

    private Long id;
    private LocalDate fecha;
    private String jornadaCita;
    private Afiliado afiliado;
    private Procedimiento procedimiento;
    private double valorCopago;
    private EstadoCita estado;

    public CitaTestDataBuilder conCitaPorDefecto(){
        this.id=2L;
        this.fecha= LocalDate.parse("2022-06-10");
        this.jornadaCita="M";
        this.afiliado=new AfiliadoTestDataBuilder().conAfiliadoPorDefecto().reconstruir();
        this.procedimiento = new ProcedimientoTestDataBuilder().conProcedimientoPorDefecto().reconstruir();
        this.valorCopago=69000;
        this.estado=EstadoCita.PENDIENTE;
        return this;
    }

    public CitaTestDataBuilder conId(Long id){
        this.id=id;
        return  this;
    }

    public CitaTestDataBuilder conFecha(LocalDate fecha){
        this.fecha=fecha;
        return  this;
    }

    public CitaTestDataBuilder conJornada(String jornadaCita){
        this.jornadaCita=jornadaCita;
        return  this;
    }

    public CitaTestDataBuilder conAfiliado(Afiliado afiliado){
        this.afiliado=afiliado;
        return  this;
    }
    public CitaTestDataBuilder conProcedimiento(Procedimiento procedimiento){
        this.procedimiento=procedimiento;
        return  this;
    }

    public CitaTestDataBuilder conValorCopago(Double valor){
        this.valorCopago=valor;
        return  this;
    }

    public CitaTestDataBuilder conEstado(EstadoCita estado){
        this.estado=estado;
        return  this;
    }

    public Cita crear(){
        return Cita.crear(new SolicitudCitaTestDataBuilder()
                .conAfiliado(afiliado)
                .conProcedimiento(procedimiento)
                .conFecha(fecha)
                .conJornada(jornadaCita)
                .build());
    }
    public Cita reconstruir(){
        return Cita.reconstruir(id,fecha,jornadaCita,afiliado,procedimiento,valorCopago,estado);
    }
}
