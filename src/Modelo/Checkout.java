/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author SARA
 */
import java.time.LocalDateTime;

public class Checkout {
    private String documentoUsuario;
    private LocalDateTime fechaHora;
    private String habitacion;

    public Checkout() {
    }

    public Checkout(String documentoUsuario, LocalDateTime fechaHora, String habitacion) {
        this.documentoUsuario = documentoUsuario;
        this.fechaHora = fechaHora;
        this.habitacion = habitacion;
    }

    public String getDocumentoUsuario() { return documentoUsuario; }
    public void setDocumentoUsuario(String documentoUsuario) { this.documentoUsuario = documentoUsuario; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getHabitacion() { return habitacion; }
    public void setHabitacion(String habitacion) { this.habitacion = habitacion; }
}

