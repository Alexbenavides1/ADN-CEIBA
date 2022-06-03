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
class ConsultaControladorCitaTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarCitasPendientes() throws Exception {

        mocMvc.perform(get("/cita/pendientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].numeroIdentificacion", is("1067111111")))
                .andExpect(jsonPath("$[0].fecha", is("2022-06-13")))
                .andExpect(jsonPath("$[0].jornada", is("M")))
                .andExpect(jsonPath("$[0].codigoProcedimiento", is("808081")))
                .andExpect(jsonPath("$[0].valorCopago", is(34500.0)))
                .andExpect(jsonPath("$[0].estado", is("PENDIENTE")));
    }

    @Test
    void consultarCitasCanceladas() throws Exception {

        mocMvc.perform(get("/cita/canceladas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].numeroIdentificacion", is("1067222222")))
                .andExpect(jsonPath("$[0].fecha", is("2022-06-14")))
                .andExpect(jsonPath("$[0].jornada", is("T")))
                .andExpect(jsonPath("$[0].codigoProcedimiento", is("808082")))
                .andExpect(jsonPath("$[0].valorCopago", is(69200.0)))
                .andExpect(jsonPath("$[0].estado", is("CANCELADA")));
    }

    @Test
    void consultarCitaPorId() throws Exception {

        mocMvc.perform(get("/cita/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].numeroIdentificacion", is("1067333333")))
                .andExpect(jsonPath("$[0].fecha", is("2022-06-15")))
                .andExpect(jsonPath("$[0].codigoProcedimiento", is("808083")))
                .andExpect(jsonPath("$[0].valorCopago", is(115000.0)))
                .andExpect(jsonPath("$[0].estado", is("PENDIENTE")));
    }
    @Test
    void consultarCitaPorAfiliado() throws Exception {

        mocMvc.perform(get("/cita/afiliado/1067333333")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].numeroIdentificacion", is("1067333333")))
                .andExpect(jsonPath("$[0].fecha", is("2022-06-15")))
                .andExpect(jsonPath("$[0].codigoProcedimiento", is("808083")))
                .andExpect(jsonPath("$[0].valorCopago", is(115000.0)))
                .andExpect(jsonPath("$[0].estado", is("PENDIENTE")));
    }

    @Test
    void consultarCitaPorFecha() throws Exception {

        mocMvc.perform(get("/cita/fecha/2022-06-15")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].numeroIdentificacion", is("1067333333")))
                .andExpect(jsonPath("$[0].fecha", is("2022-06-15")))
                .andExpect(jsonPath("$[0].codigoProcedimiento", is("808083")))
                .andExpect(jsonPath("$[0].valorCopago", is(115000.0)))
                .andExpect(jsonPath("$[0].estado", is("PENDIENTE")));
    }


}
