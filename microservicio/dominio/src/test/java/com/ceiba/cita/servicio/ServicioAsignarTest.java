package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.afiliado.AfiliadoTestDataBuilder;
import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.procedimiento.ProcedimientoTestDataBuilder;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;

public class ServicioAsignarTest {

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
                .conFecha(LocalDate.parse("2022-05-31"))
                .build();

        var repositorioCita = Mockito.mock(RepositorioCita.class);
        Mockito.when(repositorioCita.guardar(Mockito.any())).thenReturn(1L);


        var servicioCita = new ServicioAsignar(repositorioCita);

        var idCitaCreada = servicioCita.ejecutar(solicitudCita);

        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(1)).guardar(captorCita.capture());
        Assertions.assertEquals(afiliado, captorCita.getValue().getAfiliado());
        Assertions.assertEquals(procedimiento, captorCita.getValue().getProcedimiento());
        Assertions.assertEquals(51750, captorCita.getValue().getValor_copago());
        Assertions.assertEquals(1l, idCitaCreada);
    }

    @Test
    void asignarCitaAfiliadoConCitaPendienteDeberiaLanzarError(){

        var solicitudCita = new SolicitudCitaTestDataBuilder()
                .conProcedimiento(new ProcedimientoTestDataBuilder()
                        .conProcedimientoPorDefecto()
                        .reconstruir())
                .conAfiliado(new AfiliadoTestDataBuilder()
                        .conAfiliadoPorDefecto()
                        .conNumeroIdentificacion("1067555555")
                        .reconstruir())
                .conJornada("M")
                .conFecha(LocalDate.parse("2022-06-08"))
                .build();

        var repositorioCita = Mockito.mock(RepositorioCita.class);

        var servicioCita = new ServicioAsignar(repositorioCita);

        servicioCita.ejecutar(solicitudCita);

        BasePrueba.assertThrows(() -> servicioCita.
                ejecutar(solicitudCita), ExcepcionValorInvalido.class, "No se puede asignar la cita porque el afiliado ya cuenta con una cita pendiente");

    }

    @Test
    void asignarCitaSinCuposDisponiblesDeberiaLanzarError(){

        var solicitudCita1 = new SolicitudCitaTestDataBuilder()
                .conProcedimiento(new ProcedimientoTestDataBuilder()
                        .conProcedimientoPorDefecto()
                        .reconstruir())
                .conAfiliado(new AfiliadoTestDataBuilder()
                        .conAfiliadoPorDefecto()
                        .conNumeroIdentificacion("1067666666")
                        .reconstruir())
                .conJornada("M")
                .conFecha(LocalDate.parse("2022-06-07"))
                .build();

        var solicitudCita2 = new SolicitudCitaTestDataBuilder()
                .conProcedimiento(new ProcedimientoTestDataBuilder()
                        .conProcedimientoPorDefecto()
                        .reconstruir())
                .conAfiliado(new AfiliadoTestDataBuilder()
                        .conAfiliadoPorDefecto()
                        .conNumeroIdentificacion("1067777777")
                        .reconstruir())
                .conJornada("M")
                .conFecha(LocalDate.parse("2022-06-07"))
                .build();

        var solicitudCita3 = new SolicitudCitaTestDataBuilder()
                .conProcedimiento(new ProcedimientoTestDataBuilder()
                        .conProcedimientoPorDefecto()
                        .reconstruir())
                .conAfiliado(new AfiliadoTestDataBuilder()
                        .conAfiliadoPorDefecto()
                        .conNumeroIdentificacion("1067888888")
                        .reconstruir())
                .conJornada("M")
                .conFecha(LocalDate.parse("2022-06-07"))
                .build();

        var solicitudCita4 = new SolicitudCitaTestDataBuilder()
                .conProcedimiento(new ProcedimientoTestDataBuilder()
                        .conProcedimientoPorDefecto()
                        .reconstruir())
                .conAfiliado(new AfiliadoTestDataBuilder()
                        .conAfiliadoPorDefecto()
                        .conNumeroIdentificacion("1067999999")
                        .reconstruir())
                .conJornada("M")
                .conFecha(LocalDate.parse("2022-06-07"))
                .build();

        var solicitudCita5 = new SolicitudCitaTestDataBuilder()
                .conProcedimiento(new ProcedimientoTestDataBuilder()
                        .conProcedimientoPorDefecto()
                        .reconstruir())
                .conAfiliado(new AfiliadoTestDataBuilder()
                        .conAfiliadoPorDefecto()
                        .conNumeroIdentificacion("1067000000")
                        .reconstruir())
                .conJornada("M")
                .conFecha(LocalDate.parse("2022-06-07"))
                .build();

        var solicitudCita6 = new SolicitudCitaTestDataBuilder()
                .conProcedimiento(new ProcedimientoTestDataBuilder()
                        .conProcedimientoPorDefecto()
                        .reconstruir())
                .conAfiliado(new AfiliadoTestDataBuilder()
                        .conAfiliadoPorDefecto()
                        .conNumeroIdentificacion("10673333333")
                        .reconstruir())
                .conJornada("M")
                .conFecha(LocalDate.parse("2022-06-07"))
                .build();

        var repositorioCita = Mockito.mock(RepositorioCita.class);

        var servicioCita = new ServicioAsignar(repositorioCita);

        servicioCita.ejecutar(solicitudCita1);
        servicioCita.ejecutar(solicitudCita2);
        servicioCita.ejecutar(solicitudCita3);
        servicioCita.ejecutar(solicitudCita4);
        servicioCita.ejecutar(solicitudCita5);

        BasePrueba.assertThrows(() -> servicioCita.
                ejecutar(solicitudCita6), ExcepcionValorInvalido.class, "No existen cupos disponibles en la fecha y jornada seleccionada");


    }
}
