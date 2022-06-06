package com.ceiba.cita.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.afiliado.AfiliadoTestDataBuilder;
import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.afiliado.modelo.entidad.NivelAfiliado;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.modelo.entidad.JornadaCita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.procedimiento.ProcedimientoTestDataBuilder;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CitaTest {



    @Test
    void deberiaAsignarLaCitaCorrectamenteAfiliadoNivel1(){

        Afiliado afiliado = new AfiliadoTestDataBuilder()
                .conAfiliadoPorDefecto()
                .conNumeroIdentificacion("11111111")
                .conNivel(NivelAfiliado.NIVEL_I)
                .reconstruir();

        Procedimiento procedimiento = new ProcedimientoTestDataBuilder()
                .conProcedimientoPorDefecto()
                .conCodigo("111111")
                .conValor(500000)
                .reconstruir();

        var cita = new CitaTestDataBuilder()
                .conAfiliado(afiliado)
                .conProcedimiento(procedimiento)
                .conFecha(LocalDate.parse("2022-06-13"))
                .conJornada(JornadaCita.M)
                .crear();

        Assertions.assertEquals(afiliado,cita.getAfiliado());
        Assertions.assertEquals(procedimiento,cita.getProcedimiento());
        Assertions.assertEquals("M",cita.getJornada().name());
        Assertions.assertEquals(57500,cita.getValorCopago());

    }

    @Test
    void deberiaReconstruirLaCitaCorrectamente(){
        Afiliado afiliado = new AfiliadoTestDataBuilder()
                .conAfiliadoPorDefecto()
                .reconstruir();
        Procedimiento procedimiento = new ProcedimientoTestDataBuilder()
                .conProcedimientoPorDefecto()
                .reconstruir();
        var cita = new CitaTestDataBuilder()
                .conId(1L)
                .conAfiliado(afiliado)
                .conProcedimiento(procedimiento)
                .conFecha(LocalDate.parse("2022-06-13"))
                .conJornada(JornadaCita.M)
                .conValorCopago(69000.0)
                .conEstado(EstadoCita.PENDIENTE)
                .reconstruir();

        Assertions.assertEquals(1L,cita.getId());
        Assertions.assertEquals(afiliado,cita.getAfiliado());
        Assertions.assertEquals(procedimiento,cita.getProcedimiento());
        Assertions.assertEquals("M",cita.getJornada().name());
        Assertions.assertEquals(69000.0,cita.getValorCopago());
        Assertions.assertTrue(cita.esPendiente());
        Assertions.assertFalse(cita.esCancelada());
    }

    @Test
    void deberiaAsignarLaCitaCorrectamenteTopeMaximoNivel1(){

        Afiliado afiliado = new AfiliadoTestDataBuilder()
                .conAfiliadoPorDefecto()
                .conNumeroIdentificacion("22222222")
                .conNivel(NivelAfiliado.NIVEL_I)
                .reconstruir();

        Procedimiento procedimiento = new ProcedimientoTestDataBuilder()
                .conProcedimientoPorDefecto()
                .conCodigo("222222")
                .conValor(3000000)
                .reconstruir();

        var cita = new CitaTestDataBuilder()
                .conAfiliado(afiliado)
                .conProcedimiento(procedimiento)
                .conFecha(LocalDate.parse("2022-06-13"))
                .conJornada(JornadaCita.T)
                .crear();

        Assertions.assertEquals(afiliado,cita.getAfiliado());
        Assertions.assertEquals(procedimiento,cita.getProcedimiento());
        Assertions.assertEquals("T",cita.getJornada().name());
        Assertions.assertEquals(272924,cita.getValorCopago());

    }

    @Test
    void deberiaAsignarLaCitaCorrectamenteAfiliadoNivel2(){

        Afiliado afiliado = new AfiliadoTestDataBuilder()
                .conAfiliadoPorDefecto()
                .conNumeroIdentificacion("33333333")
                .conNivel(NivelAfiliado.NIVEL_II)
                .reconstruir();

        Procedimiento procedimiento = new ProcedimientoTestDataBuilder()
                .conProcedimientoPorDefecto()
                .conCodigo("333333")
                .conValor(700000)
                .reconstruir();

        var cita = new CitaTestDataBuilder()
                .conAfiliado(afiliado)
                .conProcedimiento(procedimiento)
                .conFecha(LocalDate.parse("2022-06-13"))
                .conJornada(JornadaCita.M)
                .crear();

        Assertions.assertEquals(afiliado,cita.getAfiliado());
        Assertions.assertEquals(procedimiento,cita.getProcedimiento());
        Assertions.assertEquals("M",cita.getJornada().name());
        Assertions.assertEquals(121100,Math.ceil(cita.getValorCopago()));

    }

    @Test
    void deberiaAsignarLaCitaCorrectamenteTopeMaximoNivel2(){

        Afiliado afiliado = new AfiliadoTestDataBuilder()
                .conAfiliadoPorDefecto()
                .conNumeroIdentificacion("44444444")
                .conNivel(NivelAfiliado.NIVEL_II)
                .reconstruir();

        Procedimiento procedimiento = new ProcedimientoTestDataBuilder()
                .conProcedimientoPorDefecto()
                .conCodigo("444444")
                .conValor(7000000)
                .reconstruir();

        var cita = new CitaTestDataBuilder()
                .conAfiliado(afiliado)
                .conProcedimiento(procedimiento)
                .conFecha(LocalDate.parse("2022-06-13"))
                .conJornada(JornadaCita.T)
                .crear();

        Assertions.assertEquals(afiliado,cita.getAfiliado());
        Assertions.assertEquals(procedimiento,cita.getProcedimiento());
        Assertions.assertEquals("T",cita.getJornada().name());
        Assertions.assertEquals(1093597,cita.getValorCopago());

    }

    @Test
    void deberiaAsignarLaCitaCorrectamenteAfiliadoNivel3(){

        Afiliado afiliado = new AfiliadoTestDataBuilder()
                .conAfiliadoPorDefecto()
                .conNumeroIdentificacion("55555555")
                .conNivel(NivelAfiliado.NIVEL_III)
                .reconstruir();

        Procedimiento procedimiento = new ProcedimientoTestDataBuilder()
                .conProcedimientoPorDefecto()
                .conCodigo("555555")
                .conValor(650000)
                .reconstruir();

        var cita = new CitaTestDataBuilder()
                .conAfiliado(afiliado)
                .conProcedimiento(procedimiento)
                .conFecha(LocalDate.parse("2022-06-13"))
                .conJornada(JornadaCita.M)
                .crear();

        Assertions.assertEquals(afiliado,cita.getAfiliado());
        Assertions.assertEquals(procedimiento,cita.getProcedimiento());
        Assertions.assertEquals("M",cita.getJornada().name());
        Assertions.assertEquals(149500,cita.getValorCopago());

    }

    @Test
    void deberiaAsignarLaCitaCorrectamenteTopeMaximoNivel3(){

        Afiliado afiliado = new AfiliadoTestDataBuilder()
                .conAfiliadoPorDefecto()
                .conNumeroIdentificacion("66666666")
                .conNivel(NivelAfiliado.NIVEL_III)
                .reconstruir();

        Procedimiento procedimiento = new ProcedimientoTestDataBuilder()
                .conProcedimientoPorDefecto()
                .conCodigo("666666")
                .conValor(10000000)
                .reconstruir();

        var cita = new CitaTestDataBuilder()
                .conAfiliado(afiliado)
                .conProcedimiento(procedimiento)
                .conFecha(LocalDate.parse("2022-06-13"))
                .conJornada(JornadaCita.T)
                .crear();

        Assertions.assertEquals(afiliado,cita.getAfiliado());
        Assertions.assertEquals(procedimiento,cita.getProcedimiento());
        Assertions.assertEquals("T",cita.getJornada().name());
        Assertions.assertEquals(2187195,cita.getValorCopago());

    }

    @Test
    void citaSinAfiliadoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conAfiliado(null)
                        .crear(), ExcepcionValorObligatorio.class,
                "El afiliado es requerido para asignar la cita");
    }

    @Test
    void citaSinProcedimientoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conProcedimiento(null)
                        .crear(), ExcepcionValorObligatorio.class,
                "El procedimiento es requerido para asignar la cita");
    }

    @Test
    void citaSinFechaDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conFecha(null)
                        .crear(), ExcepcionValorObligatorio.class,
                "La fecha es requerida para asignar la cita");
    }

    @Test
    void citaConFechaNoValidaDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conFecha(LocalDate.parse("2022-06-04"))
                        .crear(), ExcepcionValorInvalido.class,
                "No se permiten asignar citas con fecha anterior a la actual");
    }

    @Test
    void citaConFechaNoPermitidaDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conFecha(LocalDate.parse("2022-06-11"))
                        .crear(), ExcepcionValorInvalido.class,
                "No se permiten citas los dias Sabado y Domingo");
    }

    @Test
    void citaSinJornadaDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conJornada(null)
                        .crear(), ExcepcionValorObligatorio.class,
                "La jornada es requerida para asignar la cita");
    }

    @Test
    void reconstruirCitaSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conId(null)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "El id es requerido para asignar la cita");
    }

    @Test
    void reconstruirCitaSinFechaDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conFecha(null)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "La fecha es requerida para asignar la cita");
    }
    @Test
    void reconstruirCitaConFechaNoValidaDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conFecha(LocalDate.parse("2022-06-04"))
                        .reconstruir(), ExcepcionValorInvalido.class,
                "No se permiten asignar citas con fecha anterior a la actual");
    }

    @Test
    void reconstruirCitaConFechaNoPermitidaDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conFecha(LocalDate.parse("2022-06-11"))
                        .reconstruir(), ExcepcionValorInvalido.class,
                "No se permiten citas los dias Sabado y Domingo");
    }

    @Test
    void reconstruirCitaSinJornadaDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conJornada(null)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "La jornada es requerida para asignar la cita");
    }

    @Test
    void reconstruirCitaSinAfiliadoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conAfiliado(null)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "El afiliado es requerido para asignar la cita");
    }

    @Test
    void reconstruirCitaSinProcedimientoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conProcedimiento(null)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "El procedimiento es requerido para asignar la cita");
    }

    @Test
    void reconstruirCitaConCopagoMenorACeroDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conValorCopago((double) -1)
                        .reconstruir(), ExcepcionValorInvalido.class,
                "El valor del copago no puede ser menor o igual a 0");
    }

    @Test
    void cancelarCitaDeberiaQuedarEnEstadoCancelada(){

        var cita= new CitaTestDataBuilder().conCitaPorDefecto()
                .crear();
        cita.cancelar();
        Assertions.assertTrue(cita.esCancelada());
    }

    @Test
    void cancelarCitaEnFechaNoPermitidaDeberiaLanzarError(){

        var cita= new CitaTestDataBuilder().conCitaPorDefecto()
                .conFecha(LocalDate.parse("2022-06-05"))
                .crear();

        BasePrueba.assertThrows(() -> cita.cancelar(), ExcepcionValorInvalido.class,
                "Solo se permite cancelar la cita maximo hasta el dia habil anterior a la fecha de la cita");


    }
}
