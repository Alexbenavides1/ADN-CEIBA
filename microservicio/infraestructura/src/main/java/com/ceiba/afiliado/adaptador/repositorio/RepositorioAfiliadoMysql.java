package com.ceiba.afiliado.adaptador.repositorio;

import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.afiliado.puerto.repositorio.RepositorioAfiliado;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioAfiliadoMysql implements RepositorioAfiliado {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "afiliado",value = "obtenerporidentificacion")
    private static String sqlObtenerPorIdentificacion;

    public RepositorioAfiliadoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public Afiliado obtenerAfiliado(String numero_identificacion) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("numero_identificacion",numero_identificacion);

        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlObtenerPorIdentificacion,parameterSource,new MapeoAfiliado()));
    }
}
