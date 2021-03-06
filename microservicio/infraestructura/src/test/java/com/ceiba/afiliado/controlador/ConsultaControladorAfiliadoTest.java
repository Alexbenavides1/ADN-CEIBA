package com.ceiba.afiliado.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.afiliado.modelo.entidad.NivelAfiliado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorAfiliado.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorAfiliadoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void consultarTodosLosAfiliados()throws Exception{
        mockMvc.perform(get("/afiliado")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].numeroIdentificacion",is("1067111111")))
                .andExpect(jsonPath("$[0].nombre",is("Juan Perez")))
                .andExpect(jsonPath("$[0].nivel",is(NivelAfiliado.NIVEL_I.name())));

    }
}
