package com.ceiba.procedimiento.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.procedimiento.modelo.dto.ProcedimientoDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class MapeoProcedimientosTodos implements RowMapper<ProcedimientoDTO>, MapperResult {

    @Override
    public ProcedimientoDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        var codigo = resultSet.getString("codigo");
        var nombre = resultSet.getString("nombre");
        var valor = resultSet.getDouble("valor");

        return new ProcedimientoDTO(codigo,nombre,valor);
    }
}
