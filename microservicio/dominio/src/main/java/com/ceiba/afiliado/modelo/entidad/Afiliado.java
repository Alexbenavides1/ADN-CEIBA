package com.ceiba.afiliado.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

public class Afiliado {

    private final String numeroIdentificacion;
    private final String nombre;
    private NivelAfiliado nivelAfiliado;

    protected Afiliado(String numeroIdentificacion, String nombre, NivelAfiliado nivelAfiliado) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.nivelAfiliado = nivelAfiliado;
    }

    public static Afiliado reconstruir(String numeroIdentificacion, String nombre, NivelAfiliado nivelAfiliado){
        validarObligatorio(numeroIdentificacion,"Numero de identificación del afiliado es requerido");
        validarObligatorio(nombre,"Nombre del afiliado es requerido");
        validarObligatorio(nivelAfiliado,"Nivel del afiliado es requerido");
        return new Afiliado(numeroIdentificacion,nombre,nivelAfiliado);
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public NivelAfiliado getNivel() {
        return nivelAfiliado;
    }
}
