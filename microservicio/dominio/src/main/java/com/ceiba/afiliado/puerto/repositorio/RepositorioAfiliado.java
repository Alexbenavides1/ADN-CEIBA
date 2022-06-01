package com.ceiba.afiliado.puerto.repositorio;

import com.ceiba.afiliado.modelo.entidad.Afiliado;

public interface RepositorioAfiliado {

    Afiliado obtenerAfiliado(String numero_identificacion);
}
