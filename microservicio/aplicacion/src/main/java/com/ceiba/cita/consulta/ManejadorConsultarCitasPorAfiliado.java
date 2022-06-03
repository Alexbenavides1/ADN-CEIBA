package com.ceiba.cita.consulta;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ManejadorConsultarCitasPorAfiliado {

    private final DaoCita daoCita;

    public ManejadorConsultarCitasPorAfiliado(DaoCita daoCita) {
        this.daoCita = daoCita;
    }

    public List<ResumenCitaDTO> ejecutar(String identificacionAfiliado){
        return daoCita.obtenerCitasPorAfiliado(identificacionAfiliado);
    }
}
