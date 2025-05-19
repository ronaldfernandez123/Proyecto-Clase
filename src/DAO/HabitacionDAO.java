/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author SARA
 */

import Modelo.Habitacion;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    private List<Habitacion> habitaciones;
    private final String archivo = "habitaciones.json";
    private final Gson gson = new Gson();

    public HabitacionDAO() {
        this.habitaciones = cargarHabitaciones();
    }

    private List<Habitacion> cargarHabitaciones() {
        try (Reader reader = new FileReader(archivo)) {
            Type listType = new TypeToken<List<Habitacion>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void guardarHabitaciones() {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(habitaciones, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarHabitacion(Habitacion h) {
        habitaciones.add(h);
        guardarHabitaciones();
    }

    public Habitacion buscarPorNumero(String numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero().equals(numero)) return h;
        }
        return null;
    }

    public List<Habitacion> listarHabitaciones() {
        return habitaciones;
    }
}

