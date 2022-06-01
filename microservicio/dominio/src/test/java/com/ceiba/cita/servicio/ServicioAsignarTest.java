package com.ceiba.cita.servicio;

import com.ceiba.afiliado.AfiliadoTestDataBuilder;
import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.cliente.entidad.TipoCliente;
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

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .conTipoCliente(TipoCliente.ESPECIAL)
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
}
