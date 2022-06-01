package com.ceiba.procedimiento.puerto.repositorio;

import com.ceiba.procedimiento.modelo.entidad.Procedimiento;

public interface RepositorioProcedimiento {

    Procedimiento obtener(String codigo);
}
