package Modelo;

import java.util.Date;

public class Reserva {

    private String nombres;
    private String apellidos;
    private String tipoIdentificacion;
    private String documento;
    private String lugar;
    private String habitacion;
    private Date checkIn;
    private Date checkOut;
    private int cantidadPersonas;
    private int habitaciones;

    // Constructor completo
    public Reserva(String nombres, String apellidos, String tipoIdentificacion, String documento, String lugar,
                   String habitacion, Date checkIn, Date checkOut, int cantidadPersonas, int habitaciones) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoIdentificacion = tipoIdentificacion;
        this.documento = documento;
        this.lugar = lugar;
        this.habitacion = habitacion;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cantidadPersonas = cantidadPersonas;
        this.habitaciones = habitaciones;
    }

    // Getters y setters

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
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
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
