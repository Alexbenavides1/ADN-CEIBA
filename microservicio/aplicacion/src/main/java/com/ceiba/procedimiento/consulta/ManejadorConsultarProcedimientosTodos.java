package com.ceiba.procedimiento.consulta;

import com.ceiba.procedimiento.modelo.dto.ProcedimientoDTO;
import com.ceiba.procedimiento.puerto.dao.DaoProcedimiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarProcedimientosTodos {

    private final DaoProcedimiento daoProcedimiento;

    public ManejadorConsultarProcedimientosTodos(DaoProcedimiento daoProcedimiento) {
        this.daoProcedimiento = daoProcedimiento;
    }

    public List<ProcedimientoDTO> ejecutar(){
        return daoProcedimiento.obtenerTodos();
    }
}
