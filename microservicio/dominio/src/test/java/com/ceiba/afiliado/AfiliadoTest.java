package com.ceiba.afiliado;

import com.ceiba.BasePrueba;
import com.ceiba.afiliado.modelo.entidad.NivelAfiliado;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AfiliadoTest {

    @Test
    void deberiaCrearAfiliadoExitoso(){
        var afiliado = new AfiliadoTestDataBuilder()
                .conNumeroIdentificacion("1067901901")
                .conNombre("Afiliado prueba")
                .conNivel(NivelAfiliado.NIVEL_I)
                .reconstruir();

        Assertions.assertEquals("1067901901",afiliado.getNumeroIdentificacion());
        Assertions.assertEquals("Afiliado prueba",afiliado.getNombre());
        Assertions.assertEquals(NivelAfiliado.NIVEL_I,afiliado.getNivel());
    }

    @Test
    void reconstruirAfiliadoSinNumeroIdentificacionDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new AfiliadoTestDataBuilder()
                        .conNombre("Afiliado prueba")
                        .conNivel(NivelAfiliado.NIVEL_I)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Numero de identificación del afiliado es requerido");
    }

    @Test
    void reconstruirAfiliadoSinNombreDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new AfiliadoTestDataBuilder()
                        .conNumeroIdentificacion("1067901901")
                        .conNivel(NivelAfiliado.NIVEL_I)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Nombre del afiliado es requerido");
    }

    @Test
    void reconstruirAfiliadoSinNivelDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new AfiliadoTestDataBuilder()
                        .conNumeroIdentificacion("1067901901")
                        .conNombre("Prueba")
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Nivel del afiliado es requerido");
    }
}
