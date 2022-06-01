package com.ceiba.procedimiento.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.procedimiento.modelo.dto.ProcedimientoDTO;
import com.ceiba.procedimiento.puerto.dao.DaoProcedimiento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoProcedimientoMysql implements DaoProcedimiento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoProcedimientosTodos mapeoProcedimientosTodos;

    @SqlStatement(namespace = "procedimiento",value = "obtenertodos")
    private static String sqlObtenerTodos;

    public DaoProcedimientoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoProcedimientosTodos mapeoProcedimientosTodos) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoProcedimientosTodos = mapeoProcedimientosTodos;
    }

    @Override
    public List<ProcedimientoDTO> obtenerTodos() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerTodos,mapeoProcedimientosTodos);
    }
}
