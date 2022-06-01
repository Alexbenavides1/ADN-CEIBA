package com.ceiba.procedimiento;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProcedimientoTest {

    @Test
    void deberiaCrearProcedimientoExitoso(){
        var procedimiento = new ProcedimientoTestDataBuilder()
                .conCodigo("111222")
                .conNombre("Procedimiento prueba")
                .conValor(Double.parseDouble("300000"))
                .reconstruir();

        Assertions.assertEquals("111222",procedimiento.getCodigo());
        Assertions.assertEquals("Procedimiento prueba",procedimiento.getNombre());
        Assertions.assertEquals("300000",procedimiento.getValor());
    }

    @Test
    void reconstruirProcedimientoSinCodigoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new ProcedimientoTestDataBuilder()
                        .conNombre("Procedimiento")
                        .conValor(100000)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Codigo del procedimiento es requerido");
    }

    @Test
    void reconstruirProcedimientoSinNombreDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new ProcedimientoTestDataBuilder()
                .conCodigo("111222")
                .conValor(100000)
                .reconstruir(), ExcepcionValorObligatorio.class,
                "Nombre del procedimiento es requerido");
    }

    @Test
    void reconstruirProcedimientoSinValorDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new ProcedimientoTestDataBuilder()
                        .conCodigo("111222")
                        .conNombre("Procedimiento")
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Valor del procedimiento es requerido");
    }
}
