package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DaoCitaMysql implements DaoCita {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoCitaResumen mapeoCitaResumen;

    @SqlStatement(namespace = "cita",value = "obtenerpendientes")
    private static String sqlObtenerPendientes;

    @SqlStatement(namespace = "cita",value = "obtenercanceladas")
    private static String sqlObtenerCanceladas;

    @SqlStatement(namespace = "cita",value = "obtenerporid")
    private static String sqlObtenerCitaPorId;

    @SqlStatement(namespace = "cita",value = "obtenerporidentificacion")
    private static String sqlObtenerCitasPorAfiliado;

    @SqlStatement(namespace = "cita",value = "obtenerporfecha")
    private static String sqlObtenerCitasPorFecha;

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

    @Override
    public List<ResumenCitaDTO> obtenerCitaPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .query(sqlObtenerCitaPorId,parameterSource,mapeoCitaResumen);
    }

    @Override
    public List<ResumenCitaDTO> obtenerCitasPorAfiliado(String identificacion_afiliado) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identificacion_afiliado",identificacion_afiliado);
        return this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .query(sqlObtenerCitasPorAfiliado,parameterSource,mapeoCitaResumen);
    }

    @Override
    public List<ResumenCitaDTO> obtenerCitasPorFecha(LocalDate fecha) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("fecha",fecha.toString());
        return this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .query(sqlObtenerCitasPorFecha,parameterSource,mapeoCitaResumen);
    }
}
