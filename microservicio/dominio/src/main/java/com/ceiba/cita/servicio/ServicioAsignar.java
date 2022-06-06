package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.JornadaCita;
import com.ceiba.cita.modelo.entidad.SolicitudAsignarCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class ServicioAsignar {

    private RepositorioCita repositorioCita;
    private static final String NO_EXISTEN_CUPOS_DISPONIBLES = "No existen cupos disponibles en la fecha y jornada seleccionada";
    private static final String EXISTE_CITA_PENDIENTE = "No se puede asignar la cita porque el afiliado ya cuenta con una cita pendiente";
    private static final String JORNADA_INVALIDA = "Jornada invalida para asignar la cita";

    public ServicioAsignar(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public Long ejecutar(SolicitudAsignarCita solicitudAsignarCita){

        esJornadaValida(solicitudAsignarCita);
        existeCitaPendientePorAfiliado(solicitudAsignarCita);
        existeDisponibilidadJornada(solicitudAsignarCita);
        var cita = Cita.crear(solicitudAsignarCita);
        return repositorioCita.guardar(cita);
    }

    private void esJornadaValida(SolicitudAsignarCita solicitudAsignarCita){
        boolean res=false;

        for (JornadaCita item : JornadaCita.values()){
            if(solicitudAsignarCita.getJornadaCita().equals(item.name())){
                res=true;
            }
        }

        if(!res){
            throw new ExcepcionValorInvalido(JORNADA_INVALIDA);
        }
    }

    private void existeDisponibilidadJornada(SolicitudAsignarCita solicitudAsignarCita){
        Integer total = this.repositorioCita.existeDisponibilidadJornada(solicitudAsignarCita.getFecha(),solicitudAsignarCita.getJornadaCita());

        if(total>4){
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
