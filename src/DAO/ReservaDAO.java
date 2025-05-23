package DAO;

import Modelo.Reserva;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaDAO {

    private static final String RUTA_ARCHIVO = "reservas.json";
    private Gson gson;

    public ReservaDAO() {
        gson = new Gson();
    }

    // Carga todas las reservas desde el archivo JSON
    public List<Reserva> cargarReservas() {
        try {
            File archivo = new File(RUTA_ARCHIVO);
            if (!archivo.exists()) {
                return new ArrayList<>();
            }
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            Type tipoLista = new TypeToken<List<Reserva>>(){}.getType();
            List<Reserva> reservas = gson.fromJson(br, tipoLista);
            br.close();
            return reservas != null ? reservas : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Guarda la lista completa de reservas en el archivo JSON
    private void guardarReservas(List<Reserva> reservas) {
        try {
            FileWriter writer = new FileWriter(RUTA_ARCHIVO);
            gson.toJson(reservas, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Agrega una nueva reserva
    public void agregarReserva(Reserva reserva) {
        List<Reserva> reservas = cargarReservas();
        reservas.add(reserva);
        guardarReservas(reservas);
    }

    // Verifica si la habitación ya está reservada en las fechas indicadas
    public boolean habitacionYaReservada(String habitacion, Date nuevoCheckIn, Date nuevoCheckOut) {
        List<Reserva> reservas = cargarReservas();
        for (Reserva r : reservas) {
            if (r.getHabitacion().equalsIgnoreCase(habitacion)) {
                Date existenteCheckIn = r.getCheckIn();
                Date existenteCheckOut = r.getCheckOut();
                // Comprobar solapamiento de fechas
                if (!(nuevoCheckOut.before(existenteCheckIn) || nuevoCheckIn.after(existenteCheckOut))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Edita una reserva existente identificada por documento y fecha de check-in
    public void editarReserva(String documentoAntiguo, Date checkInAntiguo, Reserva reservaActualizada) {
        List<Reserva> reservas = cargarReservas();
        boolean encontrado = false;

        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);

            if (r.getDocumento().equals(documentoAntiguo) && r.getCheckIn().equals(checkInAntiguo)) {
                reservas.set(i, reservaActualizada);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            guardarReservas(reservas);
        } else {
            System.out.println("No se encontró la reserva para editar.");
        }
    }
    
    
}

