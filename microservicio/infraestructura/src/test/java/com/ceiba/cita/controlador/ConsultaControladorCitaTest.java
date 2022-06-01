package com.ceiba.cita.controlador;

import com.ceiba.ApplicationMock;
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
@WebMvcTest(ConsultaControladorCita.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorCitaTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarCitasPendientes() throws Exception {

        mocMvc.perform(get("/cita/pendientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].identificacion_afiliado", is("1067111111")))
                .andExpect(jsonPath("$[0].fecha", is("2022-06-01")))
                .andExpect(jsonPath("$[0].codigo_procedimiento", is(34500.0)))
                .andExpect(jsonPath("$[0].estado", is("PENDIENTE")));
    }

    @Test
    void consultarCitasCanceladas() throws Exception {

        mocMvc.perform(get("/cita/canceladas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].identificacion_afiliado", is("1067111111")))
                .andExpect(jsonPath("$[0].fecha", is("2022-06-01")))
                .andExpect(jsonPath("$[0].codigo_procedimiento", is(34500.0)))
                .andExpect(jsonPath("$[0].estado", is("CANCELADA")));
    }
}
