package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoCitaMysql implements DaoCita {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoCitaResumen mapeoCitaResumen;

    @SqlStatement(namespace = "cita",value = "obtenerpendientes")
    private static String sqlObtenerPendientes;

    @SqlStatement(namespace = "cita",value = "obtenercanceladas")
    private static String sqlObtenerCanceladas;

    public DaoCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoCitaResumen mapeoCitaResumen) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoCitaResumen = mapeoCitaResumen;
    }

    @Override
    public List<ResumenCitaDTO> obtenerResumenCitasPendientes() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPendientes,mapeoCitaResumen);
    }

    @Override
    public List<ResumenCitaDTO> obtenerResumenCitasCanceladas() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerCanceladas,mapeoCitaResumen);
    }
}
