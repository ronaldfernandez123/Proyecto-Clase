/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author SARA
 */

import Modelo.Checkin;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CheckinDAO {
    private List<Checkin> checkIns;
    private final String archivo = "checkins.json";
    private final Gson gson = new Gson();

    public CheckinDAO() {
        this.checkIns = cargarCheckIns();
    }

    private List<Checkin> cargarCheckIns() {
        try (Reader reader = new FileReader(archivo)) {
            Type listType = new TypeToken<List<Checkin>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void guardarCheckIns() {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(checkIns, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registrarCheckIn(Checkin c) {
        checkIns.add(c);
        guardarCheckIns();
    }

    public void eliminarPorDocumento(String documento) {
        checkIns.removeIf(c -> c.getDocumento().equals(documento));
        guardarCheckIns();
    }

    public List<Checkin> listarCheckIns() {
        return checkIns;
    }
}

