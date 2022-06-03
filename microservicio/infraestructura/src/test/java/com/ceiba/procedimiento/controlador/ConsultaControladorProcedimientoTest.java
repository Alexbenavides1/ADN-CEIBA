package com.ceiba.procedimiento.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.afiliado.controlador.ConsultaControladorAfiliado;
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
class ConsultaControladorProcedimientoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void consultarTodosLosProcedimientos()throws Exception{
        mockMvc.perform(get("/procedimiento")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].codigo",is("808081")))
                .andExpect(jsonPath("$[0].nombre",is("Dermabrasion")))
                .andExpect(jsonPath("$[0].valor",is(300000.0)));

    }
}
