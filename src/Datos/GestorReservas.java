
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
import java.util.Date;
import java.util.List;

public class GestorReservas {
    private static final String ARCHIVO = "data/reservas.json";
    private final Gson gson = new Gson();

    // Cargar todas las reservas desde el archivo JSON
    public List<Reserva> obtenerTodasLasReservas() {
        List<Reserva> reservas = new ArrayList<>();
        try {
            File archivo = new File(ARCHIVO);
            if (!archivo.exists()) {
                File carpeta = archivo.getParentFile();
                if (carpeta != null && !carpeta.exists()) {
                    carpeta.mkdirs();
                }
                archivo.createNewFile();
                try (FileWriter writer = new FileWriter(archivo)) {
                    writer.write("[]");
                }
            }

            try (Reader reader = new FileReader(archivo)) {
                Type tipoLista = new TypeToken<ArrayList<Reserva>>() {}.getType();
                reservas = gson.fromJson(reader, tipoLista);
                if (reservas == null) reservas = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error al leer reservas: " + e.getMessage());
        }
        return reservas;
    }

    // Guardar todas las reservas en el archivo
    private void guardarReservas(List<Reserva> reservas) {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            gson.toJson(reservas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Agregar una nueva reserva
    public void agregarReserva(Reserva nuevaReserva) {
        List<Reserva> reservas = obtenerTodasLasReservas();
        reservas.add(nuevaReserva);
        guardarReservas(reservas);
    }

    // Obtener reservas filtradas por documento
    public List<Reserva> obtenerReservasPorDocumento(String documento) {
        List<Reserva> resultado = new ArrayList<>();
        List<Reserva> todas = obtenerTodasLasReservas();

        for (Reserva r : todas) {
            if (r.getDocumento().equals(documento)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    // Eliminar reserva por documento y fecha de check-in (formateada como string)
    public boolean eliminarReserva(String documento, String checkIn) {
        List<Reserva> reservas = obtenerTodasLasReservas();
        boolean eliminado = reservas.removeIf(r -> r.getDocumento().equals(documento)
                && formatearFecha(r.getCheckIn()).equals(checkIn));
        if (eliminado) {
            guardarReservas(reservas);
        }
        return eliminado;
    }

    // Editar una reserva existente
    public void editarReserva(String documentoAntiguo, Date checkInAntiguo, Reserva nuevaReserva) {
        List<Reserva> reservas = obtenerTodasLasReservas();

        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            if (r.getDocumento().equals(documentoAntiguo)
                    && formatearFecha(r.getCheckIn()).equals(checkInAntiguo)) {
                reservas.set(i, nuevaReserva);
                guardarReservas(reservas);
                return;
            }
        }
    }

    // Formatear fecha para comparación
    private String formatearFecha(Date fecha) {
        if (fecha == null) return "";
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(fecha);
    }

    public boolean habitacionYaReservada(String numeroHabitacion, Date fechaCheckIn, Date fechaCheckOut) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}






  






