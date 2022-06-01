package com.ceiba.afiliado.adaptador.dao;

import com.ceiba.afiliado.modelo.dto.AfiliadoDTO;
import com.ceiba.afiliado.puerto.dao.DaoAfiliado;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOAfiliadoMysql implements DaoAfiliado {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoAfiliadosTodos mapeoAfiliadosTodos;

    @SqlStatement(namespace = "afiliado",value = "obtenertodos")
    private static String sqlObtenerTodos;

    public DAOAfiliadoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoAfiliadosTodos mapeoAfiliadosTodos) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoAfiliadosTodos = mapeoAfiliadosTodos;
    }

    @Override
    public List<AfiliadoDTO> obtenerTodosAfiliados() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerTodos,mapeoAfiliadosTodos);
    }
}
