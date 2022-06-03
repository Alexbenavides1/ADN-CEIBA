package com.ceiba.cita.comando.fabrica;

import com.ceiba.afiliado.puerto.repositorio.RepositorioAfiliado;
import com.ceiba.cita.comando.ComandoSolicitudAsignarCita;
import com.ceiba.cita.modelo.entidad.SolicitudAsignarCita;
import com.ceiba.procedimiento.puerto.repositorio.RepositorioProcedimiento;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudAsignarCita {

    private final RepositorioAfiliado repositorioAfiliado;
    private final RepositorioProcedimiento repositorioProcedimiento;

    public FabricaSolicitudAsignarCita(RepositorioAfiliado repositorioAfiliado, RepositorioProcedimiento repositorioProcedimiento) {
        this.repositorioAfiliado = repositorioAfiliado;
        this.repositorioProcedimiento = repositorioProcedimiento;
    }

    public SolicitudAsignarCita crear(ComandoSolicitudAsignarCita comandoSolicitudAsignarCita){

        return new SolicitudAsignarCita(repositorioAfiliado.obtenerAfiliado(comandoSolicitudAsignarCita.getIdentificacionAfiliado()),
                repositorioProcedimiento.obtener(comandoSolicitudAsignarCita.getCodigoProcedimiento()),
                comandoSolicitudAsignarCita.getFecha(),
                comandoSolicitudAsignarCita.getJornada()
                );
    }
}
