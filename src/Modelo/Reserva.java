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
    private String motivo;
    private String checkIn;
    private String checkOut;

    public Reserva() {
    }

    public Reserva(String nombres, String apellidos, String tipoIdentificacion, String documento,
                   String lugar, String motivo, String checkIn, String checkOut) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoIdentificacion = tipoIdentificacion;
        this.documento = documento;
        this.lugar = lugar;
        this.motivo = motivo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reserva(String nombres, String apellidos, String tipoId, String documento, String lugar, String motivo, Date fechaCheckIn, Date fechaCheckOut) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters
    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getDocumento() {
        return documento;
    }

    public String getLugar() {
        return lugar;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    // Setters
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}


