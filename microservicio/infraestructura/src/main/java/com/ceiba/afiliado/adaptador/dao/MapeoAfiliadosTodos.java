package com.ceiba.afiliado.adaptador.dao;

import com.ceiba.afiliado.modelo.dto.AfiliadoDTO;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class MapeoAfiliadosTodos implements RowMapper<AfiliadoDTO>, MapperResult {

    @Override
    public AfiliadoDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        var numeroIdentificacion=resultSet.getString("numero_identificacion");
        var nombre=resultSet.getString("nombre");
        var nivel=resultSet.getInt("nivel");

        return new AfiliadoDTO(numeroIdentificacion,nombre,nivel);
    }
}
