package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoCancelar;
import com.ceiba.cita.comando.ComandoSolicitudAsignarCita;
import com.ceiba.cita.comando.manejador.ManejadorAsignarCita;
import com.ceiba.cita.comando.manejador.ManejadorCancelar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador comando cita")
public class ComandoControladorCita {

    private final ManejadorAsignarCita manejadorAsignarCita;
    private final ManejadorCancelar manejadorCancelar;

    public ComandoControladorCita(ManejadorAsignarCita manejadorAsignarCita, ManejadorCancelar manejadorCancelar) {
        this.manejadorAsignarCita = manejadorAsignarCita;
        this.manejadorCancelar = manejadorCancelar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Cita",description = "Metodo utilizado para asignar una cita")
    public ComandoRespuesta<Long> asignar(@RequestBody ComandoSolicitudAsignarCita comandoSolicitudAsignarCita){
        return this.manejadorAsignarCita.ejecutar(comandoSolicitudAsignarCita);
    }

    @PostMapping("cancelar/{id}")
    @Operation(summary = "Cancelar",description = "Metodo utilizado para cancelar una cita")
    public void cancelar(@PathVariable("id") Long id){
        this.manejadorCancelar.ejecutar(new ComandoCancelar(id));
    }
}
