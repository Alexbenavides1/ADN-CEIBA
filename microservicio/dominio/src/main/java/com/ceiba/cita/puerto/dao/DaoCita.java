package com.ceiba.cita.puerto.dao;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;

import java.util.List;

public interface DaoCita {

    List<ResumenCitaDTO> obtenerResumenCitasPendientes();
    List<ResumenCitaDTO> obtenerResumenCitasCanceladas();
}
