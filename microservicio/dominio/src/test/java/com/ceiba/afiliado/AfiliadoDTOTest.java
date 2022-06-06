package com.ceiba.afiliado;

import com.ceiba.afiliado.modelo.entidad.NivelAfiliado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AfiliadoDTOTest {

    @Test
    void deberiaReconstruirAfiliadoDTOExitosamente(){
        var afiliadoDTO= new AfiliadoDTOTestDataBuilder()
                .conNumeroIdentificacion("1067000123")
                .conNombre("Alex Benavides")
                .conNivel(NivelAfiliado.NIVEL_II.name())
                .reconstruir();
        Assertions.assertEquals("1067000123",afiliadoDTO.getNumeroIdentificacion());
        Assertions.assertEquals("Alex Benavides",afiliadoDTO.getNombre());
        Assertions.assertEquals(NivelAfiliado.NIVEL_II.name(),afiliadoDTO.getNivel());
    }
}
