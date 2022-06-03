package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.CitaTestDataBuilder;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class ServicioCancelarTest {

    @Test
    void deberiaCancelarLaCitaExitosamente() {

        var repositorioCita = Mockito.mock(RepositorioCita.class);

        Mockito.when(repositorioCita.guardar(Mockito.any())).thenReturn(1l);

        var cita = new CitaTestDataBuilder().conCitaPorDefecto()
                .reconstruir();

        var servicioCancelar = new ServicioCancelar(repositorioCita);

        servicioCancelar.ejecutar(cita);

        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(1)).actualizarEstado(captorCita.capture());

        Assertions.assertTrue(captorCita.getValue().esCancelada());
    }

    @Test
    void cancelarCitaNullDeberiaLanzarError() {
        var repositorioCita = Mockito.mock(RepositorioCita.class);
        var servicioCancelar = new ServicioCancelar(repositorioCita);

        BasePrueba.assertThrows(() -> servicioCancelar.
                ejecutar(null), ExcepcionValorObligatorio.class, "No existe una cita para cancelar");


    }
}
