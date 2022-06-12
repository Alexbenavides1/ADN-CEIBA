package com.ceiba.cita.modelo.entidad;

import com.ceiba.afiliado.modelo.entidad.Afiliado;

import com.ceiba.afiliado.modelo.entidad.NivelAfiliado;
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
    private JornadaCita jornadaCita;
    private Afiliado afiliado;
    private Procedimiento procedimiento;
    private double valorCopago;
    private EstadoCita estado;

    protected Cita( LocalDate fecha, JornadaCita jornadaCita, Afiliado afiliado, Procedimiento procedimiento) {
        this.fecha = fecha;
        this.jornadaCita = jornadaCita;
        this.afiliado = afiliado;
        this.procedimiento = procedimiento;
        this.valorCopago = calcularValorCopago(afiliado.getNivel(),procedimiento.getValor());
        this.estado=EstadoCita.PENDIENTE;
    }

    protected Cita(Long id, LocalDate fecha, JornadaCita jornadaCita, Afiliado afiliado, Procedimiento procedimiento, double valorCopago, EstadoCita estado) {
        this.id = id;
        this.fecha = fecha;
        this.jornadaCita = jornadaCita;
        this.afiliado = afiliado;
        this.procedimiento = procedimiento;
        this.valorCopago = valorCopago;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public JornadaCita getJornada() {
        return jornadaCita;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    public double getValorCopago() {
        return valorCopago;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public double calcularValorCopago(NivelAfiliado nivelAfiliado, Double valorProcedimiento) {

        double valor=0;

        if(nivelAfiliado.equals(NivelAfiliado.NIVEL_I)){
             valor=calcularCopagoNivel1(valorProcedimiento);
        }
        if(nivelAfiliado.equals(NivelAfiliado.NIVEL_II)){
            valor=calcularCopagoNivel2(valorProcedimiento);
        }
        if(nivelAfiliado.equals(NivelAfiliado.NIVEL_III)){
            valor=calcularCopagoNivel3(valorProcedimiento);
        }
        return valor;
    }

    public double calcularCopagoNivel1(Double valorProcedimiento){
        double copagoProcedimiento=valorProcedimiento * PORCENTAJE_NIVEL_1;
        return copagoProcedimiento > TOPE_MAXIMO_NIVEL_1 ? TOPE_MAXIMO_NIVEL_1 : copagoProcedimiento;
    }

    public double calcularCopagoNivel2(Double valorProcedimiento){
        double copagoProcedimiento=valorProcedimiento * PORCENTAJE_NIVEL_2;
        return copagoProcedimiento > TOPE_MAXIMO_NIVEL_2 ? TOPE_MAXIMO_NIVEL_2 : copagoProcedimiento;
    }

    public double calcularCopagoNivel3(Double valorProcedimiento){
        double copagoProcedimiento=valorProcedimiento * PORCENTAJE_NIVEL_3;
        return copagoProcedimiento > TOPE_MAXIMO_NIVEL_3 ? TOPE_MAXIMO_NIVEL_3 : copagoProcedimiento;
    }

    public static Cita crear(SolicitudAsignarCita solicitudAsignarCita){

        validarObligatorio(solicitudAsignarCita.getAfiliado(),"El afiliado es requerido para asignar la cita");
        validarObligatorio(solicitudAsignarCita.getProcedimiento(),"El procedimiento es requerido para asignar la cita");
        validarObligatorio(solicitudAsignarCita.getFecha(),"La fecha es requerida para asignar la cita");
        validarObligatorio(solicitudAsignarCita.getJornadaCita(),"La jornada es requerida para asignar la cita");

        if(!esFechaValida(solicitudAsignarCita.getFecha())){
            throw new ExcepcionValorInvalido("No se permiten asignar citas con fecha anterior a la actual");
        }

        if(!esDiaHabil(solicitudAsignarCita.getFecha())){
            throw new ExcepcionValorInvalido("No se permiten citas los dias Sabado y Domingo");
        }

        return new Cita(solicitudAsignarCita.getFecha(), JornadaCita.valueOf(solicitudAsignarCita.getJornadaCita()), solicitudAsignarCita.getAfiliado(), solicitudAsignarCita.getProcedimiento());

    }

    public static Cita reconstruir(Long id, LocalDate fecha, String jornadaCita, Afiliado afiliado, Procedimiento procedimiento, double valorCopago, EstadoCita estado) {
        validarObligatorio(id,"El id es requerido para asignar la cita");
        validarObligatorio(fecha,"La fecha es requerida para asignar la cita");

        if(!esFechaValida(fecha)){
            throw new ExcepcionValorInvalido("No se permiten asignar citas con fecha anterior a la actual");
        }

        if(!esDiaHabil(fecha)){
            throw new ExcepcionValorInvalido("No se permiten citas los dias Sabado y Domingo");
        }

        validarObligatorio(jornadaCita,"La jornada es requerida para asignar la cita");
        validarObligatorio(afiliado,"El afiliado es requerido para asignar la cita");
        validarObligatorio(procedimiento,"El procedimiento es requerido para asignar la cita");

        if(valorCopago <= 0){
            throw new ExcepcionValorInvalido("El valor del copago no puede ser menor o igual a 0");
        }

        return new Cita(id,fecha,JornadaCita.valueOf(jornadaCita),afiliado,procedimiento,valorCopago,estado);
    }

    public void cancelar(){
        if(!esDiaPermitidoParaCancelar(this.fecha)){
            this.estado=EstadoCita.CANCELADA;
        }else{
            throw new ExcepcionValorInvalido("Solo se permite cancelar la cita maximo hasta el dia habil anterior a la fecha de la cita");
        }
    }

    private boolean esDiaPermitidoParaCancelar(LocalDate fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaActual= LocalDate.parse(LocalDate.now().format(formato));
        boolean res=true;
        if(fechaActual.equals(fecha) || fechaActual.isAfter(fecha) || !esDiaHabil(fechaActual)){
            res= false;
        }
        return res;
    }

    public static boolean esFechaValida(LocalDate fecha){
        boolean res=true;
        if(LocalDate.now().isAfter(fecha)){
           res=false;
        }
        return res;
    }

    public static boolean esDiaHabil(LocalDate fecha){
        boolean res=true;
        if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
         res=false;
        }
       return res;
    }

    public boolean esCancelada(){
        return this.estado.equals(EstadoCita.CANCELADA);
    }

    public boolean esPendiente(){
        return this.estado.equals(EstadoCita.PENDIENTE);
    }
}
