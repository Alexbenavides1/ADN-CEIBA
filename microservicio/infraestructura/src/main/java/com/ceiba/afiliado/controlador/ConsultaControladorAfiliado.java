package com.ceiba.afiliado.controlador;

import com.ceiba.afiliado.consulta.ManejadorConsultarAfiliadosTodos;
import com.ceiba.afiliado.modelo.dto.AfiliadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/afiliado")
@Tag(name = "Controlador consulta afiliado")
public class ConsultaControladorAfiliado {

    private final ManejadorConsultarAfiliadosTodos manejadorConsultarAfiliadosTodos;

    public ConsultaControladorAfiliado(ManejadorConsultarAfiliadosTodos manejadorConsultarAfiliadosTodos) {
        this.manejadorConsultarAfiliadosTodos = manejadorConsultarAfiliadosTodos;
    }

    @GetMapping
    @Operation(summary = "Afiliado",description = "Metodo utilizado para consultar todos los afiliados")
    public List<AfiliadoDTO> obtenerTodos(){
        return manejadorConsultarAfiliadosTodos.ejecutar();
    }
}
