package com.ceiba.cita.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorCita.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorCitaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private RepositorioCita repositorioCita;

    @Test
    void asignarCitaExitosamente() throws Exception {
        var comandoAsignarCitaTestDataBuilder = new ComandoAsignarCitaTestDataBuilder()
                .crearPorDefecto()
                .build();

        var resultado = mocMvc.perform(post("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoAsignarCitaTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaAsignarCita.class);

        var citaGuardada = repositorioCita.obtener(respuesta.getValor());

        Assertions.assertEquals("1067555555", citaGuardada.getAfiliado().getNumeroIdentificacion());
        Assertions.assertEquals("808081", citaGuardada.getProcedimiento().getCodigo());
        Assertions.assertEquals("2022-06-01", citaGuardada.getFecha().toString());
        Assertions.assertEquals("M", citaGuardada.getJornada());
        Assertions.assertEquals(51900.0, citaGuardada.getValorCopago());


    }

    @Test
    void cancelarCitaExitosamente() throws Exception {

        mocMvc.perform(post("/cita/cancelar/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        var citaCancelada = repositorioCita.obtener(1l);

        Assertions.assertTrue(citaCancelada.esCancelada());
    }
}
