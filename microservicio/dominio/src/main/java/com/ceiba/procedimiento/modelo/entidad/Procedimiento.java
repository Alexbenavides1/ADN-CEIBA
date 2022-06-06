package com.ceiba.procedimiento.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public class Procedimiento {

    private String codigo;
    private String nombre;
    private Double valor;

    protected Procedimiento(String codigo, String nombre, Double valor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.valor = valor;
    }

    public static Procedimiento reconstruir(String codigo, String nombre, Double valor) {
        ValidadorArgumento.validarObligatorio(codigo,"Codigo del procedimiento es requerido");
        ValidadorArgumento.validarObligatorio(nombre,"Nombre del procedimiento es requerido");
        ValidadorArgumento.validarObligatorio(valor,"Valor del procedimiento es requerido");
        return new Procedimiento(codigo,nombre,valor);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getValor() {
        return valor;
    }
}
