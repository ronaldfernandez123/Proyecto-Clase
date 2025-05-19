/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author SARA
 */

import Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {

    private static final String FILE_PATH = "C:\\Users\\SARA\\Documents\\NetBeansProjects\\proyectoAula\\Proyecto-Clase\\src\\resourselogin\\usuario.json";
    private Gson gson = new Gson();

    // Guarda un nuevo Usuario en el archivo JSON
    public void guardarUsuario(Usuario usuario) {
        List<Usuario> usuarios = leerUsuarios();
        usuarios.add(usuario);
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    // Lee todos los usuarios desde el archivo JSON
    public List<Usuario> leerUsuarios() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, tipoLista);
            if (usuarios == null) {
                return new ArrayList<>();
            }
            return usuarios;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de usuarios: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al parsear los datos del archivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}