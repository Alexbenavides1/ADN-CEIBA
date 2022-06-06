package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.JornadaCita;

import java.time.LocalDate;

public interface RepositorioCita {

    Long guardar(Cita cita);
    Cita obtener(Long id);
    void actualizarEstado(Cita cita);
    Integer existeDisponibilidadJornada(LocalDate fecha, JornadaCita jornadaCita);
    Integer existeCitaPendientePorAfiliado(String identificacionAfiliado);
}
