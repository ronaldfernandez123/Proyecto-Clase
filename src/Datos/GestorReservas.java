
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Administrator
 */


package Datos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Modelo.Reserva;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GestorReservas {
    private static final String ARCHIVO = "C:\\Users\\SARA\\Documents\\NetBeansProjects\\Proyecto-Clase\\src\\Resoursereservas\\misreservas.json";
    private final ArrayList<Object> reservas;

    public ArrayList<Reserva> obtenerReservasPorDocumento(String documento) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        ArrayList<Reserva> todas = obtenerTodasLasReservas();

        for (Reserva r : todas) {
            if (r.getDocumento().equals(documento)) {
                resultado.add(r);
            }
        }

        return resultado;
    }

    public ArrayList<Reserva> obtenerTodasLasReservas() {
        ArrayList<Reserva> lista = new ArrayList<>();
        try (Reader reader = new FileReader(ARCHIVO)) {
            Type tipoLista = new TypeToken<ArrayList<Reserva>>(){}.getType();
            lista = new Gson().fromJson(reader, tipoLista);
            if (lista == null) lista = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Archivo no encontrado, se creará nuevo.");
        }
        return lista;
    }

    public boolean eliminarReserva(String documento, String checkIn) {
        ArrayList<Reserva> reservas = obtenerTodasLasReservas();
        boolean eliminado = reservas.removeIf(r -> r.getDocumento().equals(documento) && r.getCheckIn().equals(checkIn));
        if (eliminado) {
            guardarReservas(reservas);
        }
        return eliminado;
    }

    public static void editarReserva(String documentoAntiguo, String checkInAntiguo, Reserva nuevaReserva) {
        GestorReservas gestor = new GestorReservas();
        ArrayList<Reserva> reservas = gestor.obtenerTodasLasReservas();

        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            if (r.getDocumento().equals(documentoAntiguo) && r.getCheckIn().equals(checkInAntiguo)) {
                reservas.set(i, nuevaReserva);
                gestor.guardarReservas(reservas);
                return;
            }
        }
    }

    private void guardarReservas(ArrayList<Reserva> reservas) {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            new Gson().toJson(reservas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Constructor
    public GestorReservas() {
        this.reservas = new ArrayList<>();
    }

    // Método para agregar una reserva
    public void agregarReserva(Reserva nuevaReserva) {
        reservas.add(nuevaReserva);
        guardarEnJSON(); // Método para persistir en JSON
    }

    // Método para cargar reservas desde JSON (si lo implementas)
    public void cargarReservas() {
        // Aquí podrías cargar reservas desde un archivo JSON
    }

    // Método simulado para guardar en JSON
    private void guardarEnJSON() {
        System.out.println("Guardando reservas en JSON...");
        // Implementar lógica para escribir reservas en un archivo JSON
    }
}





  






