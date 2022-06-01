package com.ceiba.cita.controlador;

import com.ceiba.cita.consulta.ManejadorConsultarCitasCanceladas;
import com.ceiba.cita.consulta.ManejadorConsultarCitasPendientes;
import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador para consulta de citas")
public class ConsultaControladorCita {

    private final ManejadorConsultarCitasPendientes manejadorConsultarCitasPendientes;
    private final ManejadorConsultarCitasCanceladas manejadorConsultarCitasCanceladas;

    public ConsultaControladorCita(ManejadorConsultarCitasPendientes manejadorConsultarCitasPendientes, ManejadorConsultarCitasCanceladas manejadorConsultarCitasCanceladas) {
        this.manejadorConsultarCitasPendientes = manejadorConsultarCitasPendientes;
        this.manejadorConsultarCitasCanceladas = manejadorConsultarCitasCanceladas;
    }

    @GetMapping("pendientes")
    @Operation(summary = "Pendientes",description = "Metodo utilizado para consultar las citas pendientes")
    public List<ResumenCitaDTO> obtenerPendientes(){
        return manejadorConsultarCitasPendientes.ejecutar();
    }

    @GetMapping("canceladas")
    @Operation(summary = "Canceladas",description = "Metodo utilizado para consultar las citas canceladas")
    public List<ResumenCitaDTO> obtenerCanceladas(){
        return manejadorConsultarCitasCanceladas.ejecutar();
    }
}
