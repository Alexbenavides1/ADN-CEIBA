package com.ceiba.procedimiento;

import com.ceiba.procedimiento.modelo.entidad.Procedimiento;
import org.junit.jupiter.api.Test;

public class ProcedimientoTestDataBuilder {

    private String codigo;
    private String nombre;
    private Double valor;

    @Test
    public ProcedimientoTestDataBuilder conProcedimientoPorDefecto(){
        this.codigo="111111";
        this.nombre="Procedimiento 1";
        this.valor= Double.valueOf(600000);
        return this;
    }

    @Test
    public Procedimiento reconstruir(){
        return Procedimiento.reconstruir(codigo,nombre,valor);
    }

    @Test
    public ProcedimientoTestDataBuilder conCodigo(String codigo){
        this.codigo=codigo;
        return this;
    }

    @Test
    public ProcedimientoTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    @Test
    public ProcedimientoTestDataBuilder conValor(double valor){
        this.valor=valor;
        return this;
    }
}
