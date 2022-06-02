package com.ceiba.cita.modelo.entidad;

import com.ceiba.afiliado.modelo.entidad.Afiliado;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.procedimiento.modelo.entidad.Procedimiento;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;


public class Cita {

    public static final double PORCENTAJE_NIVEL_1 = 0.115;
    public static final double TOPE_MAXIMO_NIVEL_1=272924;
    public static final double PORCENTAJE_NIVEL_2 = 0.173;
    public static final double TOPE_MAXIMO_NIVEL_2=1093597;
    public static final double PORCENTAJE_NIVEL_3 = 0.23;
    public static final double TOPE_MAXIMO_NIVEL_3= 2187195;

    private Long id;
    private LocalDate fecha;
    private String jornada;
    private Afiliado afiliado;
    private Procedimiento procedimiento;
    private double valor_copago;
    private EstadoCita estado;

    public Cita( LocalDate fecha, String jornada, Afiliado afiliado, Procedimiento procedimiento) {
        this.fecha = fecha;
        this.jornada = jornada;
        this.afiliado = afiliado;
        this.procedimiento = procedimiento;
        this.valor_copago = calcularValorCopago(afiliado.getNivel(),procedimiento.getValor());
        this.estado=EstadoCita.PENDIENTE;
    }

    public Cita(Long id, LocalDate fecha, String jornada, Afiliado afiliado, Procedimiento procedimiento, double valor_copago, EstadoCita estado) {
        this.id = id;
        this.fecha = fecha;
        this.jornada = jornada;
        this.afiliado = afiliado;
        this.procedimiento = procedimiento;
        this.valor_copago = valor_copago;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getJornada() {
        return jornada;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    public double getValor_copago() {
        return valor_copago;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public double calcularValorCopago(int nivel, Double valor_procedimiento) {
        switch (nivel){
            case 1:
                return calcularCopagoNivel1(valor_procedimiento);
            case 2:
                return calcularCopagoNivel2(valor_procedimiento);
            case 3:
                return calcularCopagoNivel3(valor_procedimiento);
            default:
                return 0;
        }
    }

    public double calcularCopagoNivel1(Double valor_procedimiento){
        double copagoProcedimiento=valor_procedimiento * PORCENTAJE_NIVEL_1;
        return copagoProcedimiento > TOPE_MAXIMO_NIVEL_1 ? TOPE_MAXIMO_NIVEL_1 : copagoProcedimiento;
    }

    public double calcularCopagoNivel2(Double valor_procedimiento){
        double copagoProcedimiento=valor_procedimiento * PORCENTAJE_NIVEL_2;
        return copagoProcedimiento > TOPE_MAXIMO_NIVEL_2 ? TOPE_MAXIMO_NIVEL_2 : copagoProcedimiento;
    }

    public double calcularCopagoNivel3(Double valor_procedimiento){
        double copagoProcedimiento=valor_procedimiento * PORCENTAJE_NIVEL_3;
        return copagoProcedimiento > TOPE_MAXIMO_NIVEL_3 ? TOPE_MAXIMO_NIVEL_3 : copagoProcedimiento;
    }

    public static Cita crear(SolicitudAsignarCita solicitudAsignarCita){
        validarObligatorio(solicitudAsignarCita.getAfiliado(),"El afiliado es requerido para asignar la cita");
        validarObligatorio(solicitudAsignarCita.getProcedimiento(),"El procedimiento es requerido para asignar la cita");
        validarObligatorio(solicitudAsignarCita.getFecha(),"La fecha es requerida para asignar la cita");
        validarObligatorio(solicitudAsignarCita.getJornada(),"La jornada es requerida para asignar la cita");

        if(!esDiaHabil(solicitudAsignarCita.getFecha())){
            throw new ExcepcionValorInvalido("No se permiten citas los dias Sabado y Domingo");
        }

        return new Cita(solicitudAsignarCita.getFecha(), solicitudAsignarCita.getJornada(), solicitudAsignarCita.getAfiliado(), solicitudAsignarCita.getProcedimiento());

    }

    public static Cita reconstruir(Long id, LocalDate fecha, String jornada, Afiliado afiliado, Procedimiento procedimiento, double valor_copago, EstadoCita estado) {
        validarObligatorio(id,"El id es requerido para asignar la cita");
        validarObligatorio(fecha,"La fecha es requerida para asignar la cita");

        if(!esDiaHabil(fecha)){
            throw new ExcepcionValorInvalido("No se permiten citas los dias Sabado y Domingo");
        }

        validarObligatorio(jornada,"La jornada es requerida para asignar la cita");
        validarObligatorio(afiliado,"El afiliado es requerido para asignar la cita");
        validarObligatorio(procedimiento,"El procedimiento es requerido para asignar la cita");

        if(valor_copago <= 0){
            throw new ExcepcionValorInvalido("El valor del copago no puede ser menor o igual a 0");
        }

        return new Cita(id,fecha,jornada,afiliado,procedimiento,valor_copago,estado);
    }

    public void cancelar(){
        if(esDiaPermitidoParaCancelar(this.fecha)){
            this.estado=EstadoCita.CANCELADA;
        }else{
            throw new ExcepcionValorInvalido("Solo se permite cancelar la cita maximo hasta el dia habil anterior a la fecha de la cita");
        }
    }

    private boolean esDiaPermitidoParaCancelar(LocalDate fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fechaActual= LocalDate.parse(LocalDate.now().format(formato));

        if(fechaActual.equals(fecha) || fechaActual.isAfter(fecha) || !esDiaHabil(fechaActual)){
            return false;
        }else{
            return true;
        }
    }

    public static boolean esDiaHabil(LocalDate fecha){
        if(fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean esCancelada(){
        return this.estado.equals(EstadoCita.CANCELADA);
    }

    public boolean esPendiente(){
        return this.estado.equals(EstadoCita.PENDIENTE);
    }
}
