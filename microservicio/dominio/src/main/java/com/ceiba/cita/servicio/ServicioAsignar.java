package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAsignarCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;

public class ServicioAsignar {

    private RepositorioCita repositorioCita;

    public ServicioAsignar(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public Long ejecutar(SolicitudAsignarCita solicitudAsignarCita){
        var cita = Cita.crear(solicitudAsignarCita);
        return repositorioCita.guardar(cita);
    }
}
