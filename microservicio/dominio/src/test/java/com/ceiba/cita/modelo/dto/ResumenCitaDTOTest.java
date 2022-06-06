package com.ceiba.cita.modelo.dto;

import com.ceiba.cita.modelo.entidad.EstadoCita;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ResumenCitaDTOTest {

    @Test
    void deberiaObtenerResumenCitaExitosamente(){

        var resumen = new ResumenCitaDTO(1L,"1067000000","Alex",
                "808081","Proc Prueba", LocalDate.parse("2022-06-10"),
                "M",34900.0, EstadoCita.PENDIENTE);

        Assertions.assertEquals(1L,resumen.getId());
        Assertions.assertEquals("1067000000",resumen.getNumeroIdentificacion());
        Assertions.assertEquals("Alex",resumen.getNombreAfiliado());
        Assertions.assertEquals("808081",resumen.getCodigoProcedimiento());
        Assertions.assertEquals("Proc Prueba",resumen.getNombreProcedimiento());
        Assertions.assertEquals("M",resumen.getJornada());
        Assertions.assertEquals(34900.0,resumen.getValorCopago());
        Assertions.assertEquals(EstadoCita.PENDIENTE,resumen.getEstado());

    }
}
