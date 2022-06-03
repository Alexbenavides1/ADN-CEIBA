package com.ceiba.afiliado.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

public class Afiliado {

    private final String numeroIdentificacion;
    private final String nombre;
    private Integer nivel;

    public Afiliado(String numeroIdentificacion, String nombre, Integer nivel) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public static Afiliado reconstruir(String numeroIdentificacion, String nombre, Integer nivel){
        validarObligatorio(numeroIdentificacion,"Numero de identificación del afiliado es requerido");
        validarObligatorio(nombre,"Nombre del afiliado es requerido");
        validarObligatorio(nivel,"Nivel del afiliado es requerido");
        return new Afiliado(numeroIdentificacion,nombre,nivel);
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNivel() {
        return nivel;
    }
}
