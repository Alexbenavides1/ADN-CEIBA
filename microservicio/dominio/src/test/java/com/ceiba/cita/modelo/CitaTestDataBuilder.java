package com.ceiba.cita.modelo;

import com.ceiba.afiliado.AfiliadoTestDataBuilder;
import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.servicio.SolicitudCitaTestDataBuilder;
import com.ceiba.procedimiento.ProcedimientoTestDataBuilder;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;

import java.time.LocalDate;

public class CitaTestDataBuilder {

    private Long id;
    private LocalDate fecha;
    private String jornada;
    private Afiliado afiliado;
    private Procedimiento procedimiento;
    private double valor_copago;
    private EstadoCita estado;

    public CitaTestDataBuilder conCitaPorDefecto(){
        this.id=2L;
        this.fecha= LocalDate.parse("2022-06-10");
        this.jornada="M";
        this.afiliado=new AfiliadoTestDataBuilder().conAfiliadoPorDefecto().reconstruir();
        this.procedimiento = new ProcedimientoTestDataBuilder().conProcedimientoPorDefecto().reconstruir();
        this.valor_copago=69000;
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

    public CitaTestDataBuilder conJornada(String jornada){
        this.jornada=jornada;
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
        this.valor_copago=valor;
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
                .conJornada(jornada)
                .build());
    }
    public Cita reconstruir(){
        return Cita.reconstruir(id,fecha,jornada,afiliado,procedimiento,valor_copago,estado);
    }
}
