package com.ceiba.afiliado.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

public class Afiliado {

    private final String numero_identificacion;
    private final String nombre;
    private Integer nivel;

    public Afiliado(String numero_identificacion, String nombre, Integer nivel) {
        this.numero_identificacion = numero_identificacion;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public static Afiliado reconstruir(String numero_identifacion, String nombre, Integer nivel){
        validarObligatorio(numero_identifacion,"Numero de identificación del afiliado es requerido");
        validarObligatorio(nombre,"Nombre del afiliado es requerido");
        validarObligatorio(nivel,"Nivel del afiliado es requerido");
        return new Afiliado(numero_identifacion,nombre,nivel);
    }

    public String getNumero_identificacion() {
        return numero_identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNivel() {
        return nivel;
    }
}
