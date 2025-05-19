/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author SARA
 */

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nombre;
    private String ciudad;
    private int limiteHabitaciones;
    private List<Habitacion> habitaciones;
    private List<Servicio> servicios;

    public Hotel(String nombre, String ciudad, int limiteHabitaciones) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.limiteHabitaciones = limiteHabitaciones;
        this.habitaciones = new ArrayList<>();
        this.servicios = new ArrayList<>();
    }

    public boolean agregarHabitacion(Habitacion habitacion) {
        if (habitaciones.size() < limiteHabitaciones) {
            habitaciones.add(habitacion);
            return true;
        } else {
            System.out.println("Límite de habitaciones alcanzado.");
            return false;
        }
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public int getLimiteHabitaciones() { return limiteHabitaciones; }
    public void setLimiteHabitaciones(int limiteHabitaciones) { this.limiteHabitaciones = limiteHabitaciones; }

    public List<Habitacion> getHabitaciones() { return habitaciones; }
    public void setHabitaciones(List<Habitacion> habitaciones) { this.habitaciones = habitaciones; }

    public List<Servicio> getServicios() { return servicios; }
    public void setServicios(List<Servicio> servicios) { this.servicios = servicios; }
}


