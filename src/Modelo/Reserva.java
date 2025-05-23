/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Administrator
 */
package Modelo;

import java.util.Date;

public class Reserva {

    private String nombres;
    private String apellidos;
    private String tipoIdentificacion;
    private String documento;
    private String lugar;
    private String Habitacion;
    private Date checkIn;
    private Date checkOut;
    private int cantidadPersonas;
    private int habitaciones;
    

    public Reserva(String nuevosNombres, String nuevosApellidos, String nuevoTipoID, String nuevoDocumento, String nuevoLugar, String nuevoMotivo, String nuevoCheckIn, String nuevoCheckOut) {
    }

    public Reserva(String nombres, String apellidos, String tipoIdentificacion, String documento, String lugar, String Habitacion, Date checkIn, Date checkOut) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoIdentificacion = tipoIdentificacion;
        this.documento = documento;
        this.lugar = lugar;
        this.Habitacion = Habitacion;
        this.checkIn = this.checkIn;
        this.checkOut = this.checkOut;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHabitacion() {
        return Habitacion;
    }

    public void setHabitacion(String Habitacion) {
        this.Habitacion = Habitacion;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setFechaCheckIn(Date fechaCheckIn) {
        this.checkIn = fechaCheckIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setFechaCheckOut(Date fechaCheckOut) {
        this.checkOut = fechaCheckOut;
    }
    
    public int getCantidadPersonas() { 
        return cantidadPersonas; 
    }
    
    public void setCantidadPersonas(int cantidadPersonas) { 
        this.cantidadPersonas = cantidadPersonas; 
    }

    public int getHabitaciones() { 
        return habitaciones; 
    }
    public void setHabitaciones(int habitaciones) { 
        this.habitaciones = habitaciones; 
    }
}
