/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author SARA
 */

import Modelo.Checkout;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDAO {
    private List<Checkout> checkOuts;
    private final String archivo = "checkouts.json";
    private final Gson gson = new Gson();

    public CheckoutDAO() {
        this.checkOuts = cargarCheckOuts();
    }

    private List<Checkout> cargarCheckOuts() {
        try (Reader reader = new FileReader(archivo)) {
            Type listType = new TypeToken<List<Checkout>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void guardarCheckOuts() {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(checkOuts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registrarCheckOut(Checkout c) {
        checkOuts.add(c);
        guardarCheckOuts();
    }

    public List<Checkout> listarCheckOuts() {
        return checkOuts;
    }
}

