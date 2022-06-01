package com.ceiba.afiliado.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public class Afiliado {

    private final String numero_identifacion;
    private final String nombre;
    private int nivel;

    public Afiliado(String numero_identifacion, String nombre, int nivel) {
        this.numero_identifacion = numero_identifacion;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public static Afiliado reconstruir(String numero_identifacion, String nombre, int nivel){
        ValidadorArgumento.validarObligatorio(numero_identifacion,"Numero de identificación del afiliado es requerido");
        ValidadorArgumento.validarObligatorio(nombre,"Nombre del afiliado es requerido");
        ValidadorArgumento.validarObligatorio(nivel,"Nivel del afiliado es requerido");
        return new Afiliado(numero_identifacion,nombre,nivel);
    }

    public String getNumero_identifacion() {
        return numero_identifacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }
}
