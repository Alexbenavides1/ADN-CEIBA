package com.ceiba.procedimiento;

import com.ceiba.procedimiento.modelo.entidad.Procedimiento;
import org.junit.jupiter.api.Test;

public class ProcedimientoTestDataBuilder {

    private String codigo;
    private String nombre;
    private Double valor;


    public ProcedimientoTestDataBuilder conProcedimientoPorDefecto(){
        this.codigo="111111";
        this.nombre="Procedimiento 1";
        this.valor= Double.valueOf(600000);
        return this;
    }


    public Procedimiento reconstruir(){
        return Procedimiento.reconstruir(codigo,nombre,valor);
    }


    public ProcedimientoTestDataBuilder conCodigo(String codigo){
        this.codigo=codigo;
        return this;
    }


    public ProcedimientoTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }


    public ProcedimientoTestDataBuilder conValor(double valor){
        this.valor=valor;
        return this;
    }
}
