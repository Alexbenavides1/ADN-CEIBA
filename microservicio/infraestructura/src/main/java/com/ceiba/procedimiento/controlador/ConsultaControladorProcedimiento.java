package com.ceiba.procedimiento.controlador;

import com.ceiba.procedimiento.consulta.ManejadorConsultarProcedimientosTodos;
import com.ceiba.procedimiento.modelo.dto.ProcedimientoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/procedimiento")
@Tag(name = "Controlador consulta procedimiento")
public class ConsultaControladorProcedimiento {

    private final ManejadorConsultarProcedimientosTodos manejadorConsultarProcedimientosTodos;

    public ConsultaControladorProcedimiento(ManejadorConsultarProcedimientosTodos manejadorConsultarProcedimientosTodos) {
        this.manejadorConsultarProcedimientosTodos = manejadorConsultarProcedimientosTodos;
    }

    @GetMapping
    @Operation(summary = "Procedimiento",description = "Metodo utilizado para consultar todos los procedimientos")
    public List<ProcedimientoDTO> obtenerTodos(){
        return manejadorConsultarProcedimientosTodos.ejecutar();
    }
}
