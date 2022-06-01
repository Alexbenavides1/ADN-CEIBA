package com.ceiba.afiliado.consulta;

import com.ceiba.afiliado.modelo.dto.AfiliadoDTO;
import com.ceiba.afiliado.puerto.dao.DaoAfiliado;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarAfiliadosTodos {

    private final DaoAfiliado daoAfiliado;

    public ManejadorConsultarAfiliadosTodos(DaoAfiliado daoAfiliado) {
        this.daoAfiliado = daoAfiliado;
    }
    public List<AfiliadoDTO> ejecutar(){
        return daoAfiliado.obtenerTodosAfiliados();
    }
}
