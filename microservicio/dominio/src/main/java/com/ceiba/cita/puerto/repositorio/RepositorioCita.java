package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;

import java.time.LocalDate;

public interface RepositorioCita {

    Long guardar(Cita cita);
    Cita obtener(Long id);
    Cita obtenerPorIdentificacion(String numero_identificacion);
    void actualizarEstado(Cita cita);
    Integer existeDisponibilidadJornada(LocalDate fecha,String jornada);
    Integer existeCitaPendientePorAfiliado(String identificacion_afiliado);
}
