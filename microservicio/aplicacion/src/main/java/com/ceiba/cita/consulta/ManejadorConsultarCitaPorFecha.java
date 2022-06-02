package com.ceiba.cita.consulta;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ManejadorConsultarCitaPorFecha {

    private final DaoCita daoCita;

    public ManejadorConsultarCitaPorFecha(DaoCita daoCita) {
        this.daoCita = daoCita;
    }

    public List<ResumenCitaDTO> ejecutar(LocalDate fecha){
        return daoCita.obtenerCitasPorFecha(fecha);
    }
}
