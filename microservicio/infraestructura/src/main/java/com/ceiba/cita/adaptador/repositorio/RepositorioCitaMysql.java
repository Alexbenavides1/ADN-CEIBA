package com.ceiba.cita.adaptador.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.JornadaCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class RepositorioCitaMysql implements RepositorioCita {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoCita mapeoCita;

    private static final String PARAMETRO_IDENTIFICACION = "identificacion_afiliado";

    @SqlStatement(namespace = "cita", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "cita", value="obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "cita", value="obtenerporidentificacion")
    private static String sqlObtenerPorIdentificacion;

    @SqlStatement(namespace = "cita", value="actualizarestado")
    private static String sqlActualizarEstado;

    @SqlStatement(namespace = "cita", value="obtenercitasporjornadayfecha")
    private static String sqlExisteDisponibilidadJornada;

    @SqlStatement(namespace = "cita", value="obtenercitaspendienteporafiliado")
    private static String sqlexisteCitaPendientePorAfiliado;

    public RepositorioCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoCita mapeoCita) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoCita = mapeoCita;
    }


    @Override
    public Long guardar(Cita cita) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PARAMETRO_IDENTIFICACION,cita.getAfiliado().getNumeroIdentificacion());
        parameterSource.addValue("codigo_procedimiento",cita.getProcedimiento().getCodigo());
        parameterSource.addValue("fecha",cita.getFecha());
        parameterSource.addValue("jornada",cita.getJornada().name());
        parameterSource.addValue("valor_copago",cita.getValorCopago());
        parameterSource.addValue("estado",cita.getEstado().name());

        return  this.customNamedParameterJdbcTemplate.crear(parameterSource,sqlCrear);

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
    public void actualizarEstado(Cita cita) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",cita.getId());
        parameterSource.addValue("estado",cita.getEstado().toString());

                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .update(sqlActualizarEstado,parameterSource);
    }

    @Override
    public Integer existeDisponibilidadJornada(LocalDate fecha, JornadaCita jornadaCita) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("fecha",fecha.toString());
        parameterSource.addValue("jornada",jornadaCita.name());

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteDisponibilidadJornada,parameterSource,Integer.class);


    }

    @Override
    public Integer existeCitaPendientePorAfiliado(String identificacionAfiliado) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PARAMETRO_IDENTIFICACION,identificacionAfiliado);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlexisteCitaPendientePorAfiliado,parameterSource,Integer.class);



    }
}
