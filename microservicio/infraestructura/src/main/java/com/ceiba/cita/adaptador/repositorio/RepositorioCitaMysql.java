package com.ceiba.cita.adaptador.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioCitaMysql implements RepositorioCita {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoCita mapeoCita;

    @SqlStatement(namespace = "cita", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "cita", value="obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "cita", value="obtenerporidentificacion")
    private static String sqlObtenerPorIdentificacion;

    @SqlStatement(namespace = "cita", value="actualizarestado")
    private static String sqlActualizarEstado;

    public RepositorioCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoCita mapeoCita) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoCita = mapeoCita;
    }


    @Override
    public Long guardar(Cita cita) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identificacion_afiliado",cita.getAfiliado().getNumero_identifacion());
        parameterSource.addValue("codigo_procedimiento",cita.getProcedimiento().getCodigo());
        parameterSource.addValue("fecha",cita.getFecha());
        parameterSource.addValue("jornada",cita.getJornada());
        parameterSource.addValue("valor_copago",cita.getValor_copago());
        parameterSource.addValue("estado",cita.getEstado().name());

        Long idCitaGuardada= this.customNamedParameterJdbcTemplate.crear(parameterSource,sqlCrear);

        return idCitaGuardada;

    }

    @Override
    public Cita obtener(Long id) {
       MapSqlParameterSource parameterSource = new MapSqlParameterSource();
       parameterSource.addValue("id",id);
       return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
               this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                       .queryForObject(sqlObtenerPorId,parameterSource,mapeoCita));
    }

    @Override
    public Cita obtenerPorIdentificacion(String numero_identificacion) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identificacion_afiliado",numero_identificacion);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlObtenerPorIdentificacion,parameterSource,mapeoCita));
    }

    @Override
    public void actualizarEstado(Cita cita) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",cita.getId());
        parameterSource.addValue("estado",cita.getEstado().toString());

                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .update(sqlActualizarEstado,parameterSource);
    }
}
