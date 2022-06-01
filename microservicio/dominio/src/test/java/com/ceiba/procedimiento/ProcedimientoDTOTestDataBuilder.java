package com.ceiba.procedimiento;

import com.ceiba.procedimiento.modelo.dto.ProcedimientoDTO;

public class ProcedimientoDTOTestDataBuilder {

    private String codigo;
    private String nombre;
    private Double valor;

    public ProcedimientoDTOTestDataBuilder conProcedimientoDTOPorDefecto(){
        this.codigo = "112233";
        this.nombre = "Proc Prueba";
        this.valor = Double.valueOf(250000);
        return this;
    }

    public ProcedimientoDTO reconstruir(){
        return new ProcedimientoDTO(codigo,nombre,valor);
    }

    public ProcedimientoDTOTestDataBuilder conCodigo(String codigo){
        this.codigo=codigo;
        return this;
    }

    public ProcedimientoDTOTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public ProcedimientoDTOTestDataBuilder conValor(Double valor){
        this.valor=valor;
        return this;
    }
}
