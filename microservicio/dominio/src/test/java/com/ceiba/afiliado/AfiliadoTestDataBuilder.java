package com.ceiba.afiliado;


import com.ceiba.afiliado.modelo.entidad.Afiliado;
import com.ceiba.afiliado.modelo.entidad.NivelAfiliado;

public class AfiliadoTestDataBuilder {

    private  String numero_identificacion;
    private  String nombre;
    private NivelAfiliado nivelAfiliado;

    public AfiliadoTestDataBuilder conAfiliadoPorDefecto(){
        this.numero_identificacion="1067000000";
        this.nombre="Afiliado 1";
        this.nivelAfiliado=NivelAfiliado.NIVEL_I;
        return this;
    }

    public Afiliado reconstruir(){
        return Afiliado.reconstruir(numero_identificacion,nombre,nivelAfiliado);
    }

    public AfiliadoTestDataBuilder conNumeroIdentificacion(String numero_identificacion){
        this.numero_identificacion=numero_identificacion;
        return this;
    }

    public AfiliadoTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public AfiliadoTestDataBuilder conNivel(NivelAfiliado nivelAfiliado){
        this.nivelAfiliado=nivelAfiliado;
        return  this;
    }

}
