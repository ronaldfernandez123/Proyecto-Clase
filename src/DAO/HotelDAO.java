/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author SARA
 */

import Modelo.Hotel;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private List<Hotel> hoteles;
    private final String archivo = "hoteles.json";
    private final Gson gson = new Gson();

    public HotelDAO() {
        this.hoteles = cargarHoteles();
    }

    private List<Hotel> cargarHoteles() {
        try (Reader reader = new FileReader(archivo)) {
            Type listType = new TypeToken<List<Hotel>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void guardarHoteles() {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(hoteles, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarHotel(Hotel hotel) {
        hoteles.add(hotel);
        guardarHoteles();
    }

    public Hotel buscarHotel(String nombre) {
        for (Hotel h : hoteles) {
            if (h.getNombre().equalsIgnoreCase(nombre)) return h;
        }
        return null;
    }

    public List<Hotel> listarHoteles() {
        return hoteles;
    }
}

