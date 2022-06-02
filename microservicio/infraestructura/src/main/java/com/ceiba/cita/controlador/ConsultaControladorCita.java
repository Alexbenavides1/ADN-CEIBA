package com.ceiba.cita.controlador;

import com.ceiba.cita.consulta.*;
import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador para consulta de citas")
public class ConsultaControladorCita {

    private final ManejadorConsultarCitasPendientes manejadorConsultarCitasPendientes;
    private final ManejadorConsultarCitasCanceladas manejadorConsultarCitasCanceladas;

    private final ManejadorConsultarCitaPorId manejadorConsultarCitaPorId;
    private final ManejadorConsultarCitasPorAfiliado manejadorConsultarCitasPorAfiliado;
    private final ManejadorConsultarCitaPorFecha manejadorConsultarCitaPorFecha;

    public ConsultaControladorCita(ManejadorConsultarCitasPendientes manejadorConsultarCitasPendientes,
                                   ManejadorConsultarCitasCanceladas manejadorConsultarCitasCanceladas,
                                   ManejadorConsultarCitaPorId manejadorConsultarCitaPorId,
                                   ManejadorConsultarCitasPorAfiliado manejadorConsultarCitasPorAfiliado,
                                   ManejadorConsultarCitaPorFecha manejadorConsultarCitaPorFecha) {
        this.manejadorConsultarCitasPendientes = manejadorConsultarCitasPendientes;
        this.manejadorConsultarCitasCanceladas = manejadorConsultarCitasCanceladas;
        this.manejadorConsultarCitaPorId = manejadorConsultarCitaPorId;
        this.manejadorConsultarCitasPorAfiliado = manejadorConsultarCitasPorAfiliado;
        this.manejadorConsultarCitaPorFecha=manejadorConsultarCitaPorFecha;
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

    @GetMapping("/{id}")
    @Operation(summary = "Obtener por id",description = "Metodo utilizado para consultar una cita por el id")
    public List<ResumenCitaDTO> obtenerPorId(@PathVariable("id") Long id){
        return manejadorConsultarCitaPorId.ejecutar(id);
    }

    @GetMapping("afiliado/{identificacion}")
    @Operation(summary = "Obtener por identificacion del afiliado",description = "Metodo utilizado para consultar las citas por un numero de identificacion")
    public List<ResumenCitaDTO> obtenerPorIdentificacion(@PathVariable("identificacion") String identificacion){
        return manejadorConsultarCitasPorAfiliado.ejecutar(identificacion);
    }

    @GetMapping("fecha/{fecha}")
    @Operation(summary = "Obtener por fecha",description = "Metodo utilizado para consultar las citas por fecha")
    public List<ResumenCitaDTO> obtenerPorFecha(@PathVariable("fecha") String fecha){
        return manejadorConsultarCitaPorFecha.ejecutar(LocalDate.parse(fecha));
    }
}
