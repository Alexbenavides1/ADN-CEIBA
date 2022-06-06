package com.ceiba.cita.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoSolicitudAsignarCita;
import com.ceiba.cita.comando.fabrica.FabricaSolicitudAsignarCita;
import com.ceiba.cita.servicio.ServicioAsignar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAsignarCita implements ManejadorComandoRespuesta<ComandoSolicitudAsignarCita, ComandoRespuesta<Long>> {

    private final FabricaSolicitudAsignarCita fabricaSolicitudAsignarCita;
    private final ServicioAsignar servicioAsignar;

    public ManejadorAsignarCita(FabricaSolicitudAsignarCita fabricaSolicitudAsignarCita, ServicioAsignar servicioAsignar) {
        this.fabricaSolicitudAsignarCita = fabricaSolicitudAsignarCita;
        this.servicioAsignar = servicioAsignar;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudAsignarCita comandoSolicitudAsignarCita) {

        return new ComandoRespuesta<>(servicioAsignar
                .ejecutar(fabricaSolicitudAsignarCita.crear(comandoSolicitudAsignarCita)));
    }
}
