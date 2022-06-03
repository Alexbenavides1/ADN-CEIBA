package com.ceiba.cita.puerto.dao;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;

import java.time.LocalDate;
import java.util.List;

public interface DaoCita {

    List<ResumenCitaDTO> obtenerResumenCitasPendientes();
    List<ResumenCitaDTO> obtenerResumenCitasCanceladas();

    List<ResumenCitaDTO> obtenerCitaPorId(Long id);

    List<ResumenCitaDTO> obtenerCitasPorAfiliado(String identificacionAfiliado);
    List<ResumenCitaDTO> obtenerCitasPorFecha(LocalDate fecha);
}
