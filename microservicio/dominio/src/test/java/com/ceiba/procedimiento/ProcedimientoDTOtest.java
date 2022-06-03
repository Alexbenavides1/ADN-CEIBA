package com.ceiba.procedimiento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProcedimientoDTOTest {

    @Test
    void deberiaReconstruirProcedimientoDTOExitosamente(){
        var procedimientoDTO= new ProcedimientoDTOTestDataBuilder()
                .conCodigo("111222")
                .conNombre("Procedimiento")
                .conValor(540000.0)
                .reconstruir();
        Assertions.assertEquals("111222",procedimientoDTO.getCodigo());
        Assertions.assertEquals("Procedimiento",procedimientoDTO.getNombre());
        Assertions.assertEquals(540000.0,procedimientoDTO.getValor());

    }
}
