package com.ceiba.procedimiento.puerto.dao;

import com.ceiba.procedimiento.modelo.dto.ProcedimientoDTO;

import java.util.List;

public interface DaoProcedimiento {

    List<ProcedimientoDTO> obtenerTodos();
}
