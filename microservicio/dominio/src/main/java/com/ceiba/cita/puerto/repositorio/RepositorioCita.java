package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;

public interface RepositorioCita {

    Long guardar(Cita cita);
    Cita obtener(Long id);
    Cita obtenerPorIdentificacion(String numero_identificacion);
    void actualizarEstado(Cita cita);
}
