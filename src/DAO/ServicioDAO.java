/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author SARA
 */

import Modelo.Servicio;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {
    private List<Servicio> servicios;
    private final String archivo = "servicios.json";
    private final Gson gson = new Gson();

    public ServicioDAO() {
        this.servicios = cargarServicios();
    }

    private List<Servicio> cargarServicios() {
        try (Reader reader = new FileReader(archivo)) {
            Type listType = new TypeToken<List<Servicio>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void guardarServicios() {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(servicios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarServicio(Servicio s) {
        servicios.add(s);
        guardarServicios();
    }

    public void eliminarServicio(String nombre) {
        servicios.removeIf(s -> s.getNombre().equalsIgnoreCase(nombre));
        guardarServicios();
    }

    public List<Servicio> listarServicios() {
        return servicios;
    }
}

