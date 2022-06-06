package com.ceiba.afiliado;

import com.ceiba.afiliado.modelo.dto.AfiliadoDTO;
import com.ceiba.afiliado.modelo.entidad.NivelAfiliado;

public class AfiliadoDTOTestDataBuilder {

    private  String numero_identifacion;
    private  String nombre;
    private String nivel;

    public AfiliadoDTOTestDataBuilder conAfiliadoDTOPorDefecto(){
        this.numero_identifacion="123000123";
        this.nombre="Afiliado 2";
        this.nivel= NivelAfiliado.NIVEL_I.name();
        return this;
    }

    public AfiliadoDTO reconstruir(){
        return new AfiliadoDTO(numero_identifacion,nombre,nivel);
    }

    public AfiliadoDTOTestDataBuilder conNumeroIdentificacion(String numero_identifacion){
        this.numero_identifacion=numero_identifacion;
        return this;
    }

    public AfiliadoDTOTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public AfiliadoDTOTestDataBuilder conNivel(String nivel){
        this.nivel=nivel;
        return  this;
    }
}
