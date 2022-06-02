package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class MapeoCitaResumen implements RowMapper<ResumenCitaDTO>, MapperResult {


    @Override
    public ResumenCitaDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var nombre_afiliado = resultSet.getString("nombre_afiliado");
        var identificacion_afiliado = resultSet.getString("identificacion_afiliado");
        var codigo_procedimiento = resultSet.getString("codigo_procedimiento");
        var nombre_procedimiento = resultSet.getString("nombre_procedimiento");
        var fecha = resultSet.getDate("fecha").toLocalDate();
        var jornada = resultSet.getString("jornada");
        var valor_copago = resultSet.getDouble("valor_copago");
        var estado = EstadoCita.valueOf(resultSet.getString("estado"));

        return new ResumenCitaDTO(id,identificacion_afiliado,nombre_afiliado,codigo_procedimiento,nombre_procedimiento,fecha,jornada,valor_copago,estado);
    }
}
