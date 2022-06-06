package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.afiliado.AfiliadoTestDataBuilder;
import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.JornadaCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.procedimiento.ProcedimientoTestDataBuilder;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;

class ServicioAsignarTest {

    @Test
    void deberiaAsignarCitaYGuardar(){

        Afiliado afiliado = new AfiliadoTestDataBuilder()
                .conAfiliadoPorDefecto()
                .reconstruir();

        Procedimiento procedimiento = new ProcedimientoTestDataBuilder()
                .conProcedimientoPorDefecto()
                .conValor(450000)
                .reconstruir();


        var solicitudCita = new SolicitudCitaTestDataBuilder()
                .conProcedimiento(procedimiento)
                .conAfiliado(afiliado)
                .conJornada("M")
                .conFecha(LocalDate.parse("2022-06-10"))
                .build();

        var repositorioCita = Mockito.mock(RepositorioCita.class);
        Mockito.when(repositorioCita.guardar(Mockito.any())).thenReturn(1L);


        var servicioCita = new ServicioAsignar(repositorioCita);

        var idCitaCreada = servicioCita.ejecutar(solicitudCita);


        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(1)).guardar(captorCita.capture());
        Assertions.assertEquals(afiliado, captorCita.getValue().getAfiliado());
        Assertions.assertEquals(procedimiento, captorCita.getValue().getProcedimiento());
        Assertions.assertEquals(51750, captorCita.getValue().getValorCopago());
        Assertions.assertEquals(1l, idCitaCreada);
    }

    @Test
    void asignarCitaConJornadaInvalidaDeberiaLanzarError(){

        var solicitudCita = new SolicitudCitaTestDataBuilder()
                .conJornada("A")
                .build();

        var repositorioCita = Mockito.mock(RepositorioCita.class);

        var servicioCita = new ServicioAsignar(repositorioCita);

        BasePrueba.assertThrows(() -> servicioCita.ejecutar(solicitudCita),ExcepcionValorInvalido.class,
                "Jornada invalida para asignar la cita");

    }

    @Test
    void asignarCitaAfiliadoConCitaPendienteDeberiaLanzarError(){

        var solicitudCita = new SolicitudCitaTestDataBuilder()
                .build();

        var repositorioCita = Mockito.mock(RepositorioCita.class);
        Mockito.when(repositorioCita.existeCitaPendientePorAfiliado(Mockito.anyString())).thenReturn(1);

        var servicioCita = new ServicioAsignar(repositorioCita);

        BasePrueba.assertThrows(() -> servicioCita.ejecutar(solicitudCita),ExcepcionDuplicidad.class,
                "No se puede asignar la cita porque el afiliado ya cuenta con una cita pendiente");

    }

    @Test
    void asignarCitaSinCuposDisponiblesDeberiaLanzarError(){

        var solicitudCita = new SolicitudCitaTestDataBuilder()
                .build();

        var repositorioCita = Mockito.mock(RepositorioCita.class);
        Mockito.when(repositorioCita.existeDisponibilidadJornada(Mockito.any(),Mockito.any())).thenReturn(5);

        var servicioCita = new ServicioAsignar(repositorioCita);

        BasePrueba.assertThrows(() -> servicioCita.
                ejecutar(solicitudCita), ExcepcionValorInvalido.class, "No existen cupos disponibles en la fecha y jornada seleccionada");

    }
}
