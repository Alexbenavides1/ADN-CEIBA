package com.ceiba.cita.adaptador.repositorio;

import com.ceiba.afiliado.puerto.repositorio.RepositorioAfiliado;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.procedimiento.puerto.repositorio.RepositorioProcedimiento;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoCita implements RowMapper<Cita>, MapperResult {

    private final RepositorioAfiliado repositorioAfiliado;
    private final RepositorioProcedimiento repositorioProcedimiento;

    public MapeoCita(RepositorioAfiliado repositorioAfiliado, RepositorioProcedimiento repositorioProcedimiento) {
        this.repositorioAfiliado = repositorioAfiliado;
        this.repositorioProcedimiento = repositorioProcedimiento;
    }


    @Override
    public Cita mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var identificacion_afiliado=resultSet.getString("identificacion_afiliado");
        var procedimiento=resultSet.getString("codigo_procedimiento");
        var fecha = resultSet.getDate("fecha").toLocalDate();
        var jornada=resultSet.getString("jornada");
        var valor_copago=resultSet.getDouble("valor_copago");
        var estado= EstadoCita.valueOf(resultSet.getString("estado"));

        return Cita.reconstruir(id,fecha,jornada,repositorioAfiliado.obtenerAfiliado(identificacion_afiliado),
                repositorioProcedimiento.obtener(procedimiento),valor_copago,estado);
    }
}
