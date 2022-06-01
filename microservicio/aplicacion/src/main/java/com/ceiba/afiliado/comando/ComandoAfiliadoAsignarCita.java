package com.ceiba.afiliado.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoAfiliadoAsignarCita {

    private String numero_identificacion;
    private String nombre;
    private int nivel;
}
