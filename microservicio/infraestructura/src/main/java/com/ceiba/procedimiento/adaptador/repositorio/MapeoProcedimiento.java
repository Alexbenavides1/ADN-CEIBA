package com.ceiba.procedimiento.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoProcedimiento implements RowMapper<Procedimiento>, MapperResult {

    @Override
    public Procedimiento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var codigo = resultSet.getString("codigo");
        var nombre = resultSet.getString("nombre");
        var valor = resultSet.getDouble("valor");

        return Procedimiento.reconstruir(codigo,nombre,valor);
    }
}
