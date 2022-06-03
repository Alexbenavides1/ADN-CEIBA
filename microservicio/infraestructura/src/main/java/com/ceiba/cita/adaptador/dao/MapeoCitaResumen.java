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
        var nombreAfiliado = resultSet.getString("nombre_afiliado");
        var identificacionAfiliado = resultSet.getString("identificacion_afiliado");
        var codigoProcedimiento = resultSet.getString("codigo_procedimiento");
        var nombreProcedimiento = resultSet.getString("nombre_procedimiento");
        var fecha = resultSet.getDate("fecha").toLocalDate();
        var jornada = resultSet.getString("jornada");
        var valorCopago = resultSet.getDouble("valor_copago");
        var estado = EstadoCita.valueOf(resultSet.getString("estado"));

        return new ResumenCitaDTO(id,identificacionAfiliado,nombreAfiliado,codigoProcedimiento,nombreProcedimiento,fecha,jornada,valorCopago,estado);
    }
}
