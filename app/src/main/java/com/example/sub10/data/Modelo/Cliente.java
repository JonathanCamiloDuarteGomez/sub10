package com.example.sub10.data.Modelo;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class Cliente {

    private String cedula;
    private String nombre;
    private String contraseña;
    private String correo;
    private String telefono;
    private String direccion;

    public Cliente(String cedula, String nombre, String contraseña, String correo, String telefono, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters para los atributos

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }


    public static List<Cliente> obtenerClientesSimulados() {
        List<Cliente> listaClientes = new ArrayList<>();

        listaClientes.add(new Cliente("123456789", "Admin User", "admin", "admin", "1234567890", "Admin Address"));
        listaClientes.add(new Cliente("987654321", "Juan Pérez", "1", "juan", "9876543210", "Address 1"));
        listaClientes.add(new Cliente("111111111", "María González", "2", "maria.gonzalez@gmail.com", "1111111111", "Address 2"));
        listaClientes.add(new Cliente("222222222",  "Luis Rodríguez", "3", "luis.rodriguez@gmail.com", "2222222222", "Address 3"));
        listaClientes.add(new Cliente("333333333", "Carlos Sánchez", "4", "carlos.sanchez@gmail.com", "3333333333", "Address 4"));
        listaClientes.add(new Cliente("444444444", "Laura López", "5", "laura.lopez@gmail.com", "4444444444", "Address 5"));
        return listaClientes;
    }

}
