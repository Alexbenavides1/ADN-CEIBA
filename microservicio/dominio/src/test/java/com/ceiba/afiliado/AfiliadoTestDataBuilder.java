package com.ceiba.afiliado;


import com.ceiba.afiliado.modelo.entidad.Afiliado;

public class AfiliadoTestDataBuilder {

    private  String numero_identificacion;
    private  String nombre;
    private Integer nivel;

    public AfiliadoTestDataBuilder conAfiliadoPorDefecto(){
        this.numero_identificacion="1067000000";
        this.nombre="Afiliado 1";
        this.nivel=1;
        return this;
    }

    public Afiliado reconstruir(){
        return Afiliado.reconstruir(numero_identificacion,nombre,nivel);
    }

    public AfiliadoTestDataBuilder conNumeroIdentificacion(String numero_identificacion){
        this.numero_identificacion=numero_identificacion;
        return this;
    }

    public AfiliadoTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public AfiliadoTestDataBuilder conNivel(Integer nivel){
        this.nivel=nivel;
        return  this;
    }

}
