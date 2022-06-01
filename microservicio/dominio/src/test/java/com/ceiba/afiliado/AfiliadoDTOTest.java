package com.ceiba.afiliado;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AfiliadoDTOTest {

    @Test
    void deberiaReconstruirAfiliadoDTOExitosamente(){
        var afiliadoDTO= new AfiliadoDTOTestDataBuilder()
                .conNumeroIdentificacion("1067000123")
                .conNombre("Alex Benavides")
                .conNivel(2)
                .reconstruir();
        Assertions.assertEquals("1067000123",afiliadoDTO.getNumero_identificacion());
        Assertions.assertEquals("Alex Benavides",afiliadoDTO.getNombre());
        Assertions.assertEquals(2,afiliadoDTO.getNivel());
    }
}
