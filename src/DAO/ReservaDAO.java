/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author SARA
 */

import Modelo.Reserva;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private List<Reserva> reservas;
    private final String archivo = "reservas.json";
    private final Gson gson = new Gson();

    public ReservaDAO() {
        this.reservas = cargarReservas();
    }

    private List<Reserva> cargarReservas() {
        try (Reader reader = new FileReader(archivo)) {
            Type listType = new TypeToken<List<Reserva>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void guardarReservas() {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(reservas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarReserva(Reserva r) {
        reservas.add(r);
        guardarReservas();
    }

    public List<Reserva> listarReservas() {
        return reservas;
    }

    public void eliminarReserva(String documento) {
        reservas.removeIf(r -> r.getDocumento().equals(documento));
        guardarReservas();
    }

    public Reserva buscarPorDocumento(String documento) {
        for (Reserva r : reservas) {
            if (r.getDocumento().equals(documento)) return r;
        }
        return null;
    }
}

