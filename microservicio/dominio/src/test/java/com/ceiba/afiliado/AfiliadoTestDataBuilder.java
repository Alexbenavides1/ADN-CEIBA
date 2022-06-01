package com.ceiba.afiliado;


import com.ceiba.afiliado.modelo.entidad.Afiliado;

public class AfiliadoTestDataBuilder {

    private  String numero_identifacion;
    private  String nombre;
    private int nivel;

    public AfiliadoTestDataBuilder conAfiliadoPorDefecto(){
        this.numero_identifacion="1067000000";
        this.nombre="Afiliado 1";
        this.nivel=1;
        return this;
    }

    public Afiliado reconstruir(){
        return Afiliado.reconstruir(numero_identifacion,nombre,nivel);
    }

    public AfiliadoTestDataBuilder conNumeroIdentificacion(String numero_identifacion){
        this.numero_identifacion=numero_identifacion;
        return this;
    }

    public AfiliadoTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public AfiliadoTestDataBuilder conNivel(int nivel){
        this.nivel=nivel;
        return  this;
    }

}
