package com.ceiba.procedimiento.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoProcedimientoAsignarCita {

    private String codigo;
    private String nombre;
    private Double valor;
}
