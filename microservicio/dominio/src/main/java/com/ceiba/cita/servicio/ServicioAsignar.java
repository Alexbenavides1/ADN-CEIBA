package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAsignarCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class ServicioAsignar {

    private RepositorioCita repositorioCita;
    private static final String NO_EXISTEN_CUPOS_DISPONIBLES = "No existen cupos disponibles en la fecha y jornada seleccionada";
    private static final String EXISTE_CITA_PENDIENTE = "No se puede asignar la cita porque el afiliado ya cuenta con una cita pendiente";

    public ServicioAsignar(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public Long ejecutar(SolicitudAsignarCita solicitudAsignarCita){

        existeCitaPendientePorAfiliado(solicitudAsignarCita);
        existeDisponibilidadJornada(solicitudAsignarCita);
        var cita = Cita.crear(solicitudAsignarCita);
        return repositorioCita.guardar(cita);
    }

    private void existeDisponibilidadJornada(SolicitudAsignarCita solicitudAsignarCita){
        Integer total = this.repositorioCita.existeDisponibilidadJornada(solicitudAsignarCita.getFecha(),solicitudAsignarCita.getJornada());

        if(total>=5){
            throw new ExcepcionValorInvalido(NO_EXISTEN_CUPOS_DISPONIBLES);
        }
    }

    private void existeCitaPendientePorAfiliado(SolicitudAsignarCita solicitudAsignarCita){
        Integer total = this.repositorioCita.existeCitaPendientePorAfiliado(solicitudAsignarCita.getAfiliado().getNumeroIdentificacion());
        if(total>0){
            throw new ExcepcionDuplicidad(EXISTE_CITA_PENDIENTE);
        }
    }
}
