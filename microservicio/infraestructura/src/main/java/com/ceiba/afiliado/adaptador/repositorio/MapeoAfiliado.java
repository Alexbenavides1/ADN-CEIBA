package com.ceiba.afiliado.adaptador.repositorio;

import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoAfiliado implements RowMapper<Afiliado>, MapperResult {

    @Override
    public Afiliado mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var numero_identificacion=resultSet.getString("numero_identificacion");
        var nombre=resultSet.getString("nombre");
        var nivel=resultSet.getInt("nivel");

        return Afiliado.reconstruir(numero_identificacion,nombre,nivel);
    }

}
