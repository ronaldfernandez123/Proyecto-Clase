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
        File f = new File(archivo);
        if (!f.exists()) {
            // Si no existe, crea archivo vacío
            guardarHabitaciones(new ArrayList<>());
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(archivo)) {
            Type listType = new TypeToken<List<Habitacion>>() {}.getType();
            List<Habitacion> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void guardarHabitaciones(List<Habitacion> lista) {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarHabitaciones() {
        guardarHabitaciones(this.habitaciones);
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

    // NUEVO: Eliminar habitación
    public boolean eliminarHabitacion(String numero) {
        Habitacion h = buscarPorNumero(numero);
        if (h != null) {
            habitaciones.remove(h);
            guardarHabitaciones();
            return true;
        }
        return false;
    }

    // NUEVO: Editar habitación
    public boolean editarHabitacion(String numero, Habitacion nueva) {
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getNumero().equals(numero)) {
                habitaciones.set(i, nueva);
                guardarHabitaciones();
                return true;
            }
        }
        return false;
    }
}
