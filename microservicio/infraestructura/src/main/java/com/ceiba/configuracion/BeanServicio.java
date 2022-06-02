package com.ceiba.configuracion;

import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioAsignar;
import com.ceiba.cita.servicio.ServicioCancelar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {


    @Bean
    public ServicioAsignar servicioAsignar(RepositorioCita repositorioCita) {
        return new ServicioAsignar(repositorioCita);
    }

    @Bean
    public ServicioCancelar servicioCancelar(RepositorioCita repositorioCita) {
        return new ServicioCancelar(repositorioCita);
    }


}
