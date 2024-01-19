package com.example.sub10.data.Modelo;

import java.util.ArrayList;
import java.util.List;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    // Constructor vacío requerido por Room
    public Producto() {
    }

    // Constructor que coincide con los campos
    public Producto(int id, String nombre, String descripcion, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public static List<Producto> obtenerProductosSimulados() {
        List<Producto> listaProductos = new ArrayList<>();

        // Sublimados - Ropa
        listaProductos.add(new Producto(1, "Camiseta Sublimada", "Camiseta", 25.0, 100));
        listaProductos.add(new Producto(2, "Gorra Sublimada", "Gorra", 15.0, 50));
        listaProductos.add(new Producto(3, "Pantalón Deportivo Sublimado", "Pantalón", 35.0, 75));

        // Sublimados - Accesorios
        listaProductos.add(new Producto(4, "Forro para Celular Personalizado", "Forro", 20.0, 60));
        listaProductos.add(new Producto(5, "Taza Sublimada con Foto", "Taza", 18.0, 30));
        listaProductos.add(new Producto(6, "Mousepad Personalizado", "Accesorio de Computadora", 12.0, 45));

        // Sublimados - Artículos de Hogar
        listaProductos.add(new Producto(7, "Cojín Sublimado", "Decoración para el Hogar", 22.0, 80));
        listaProductos.add(new Producto(8, "Manta Personalizada", "Manta", 30.0, 120));
        listaProductos.add(new Producto(9, "Toalla de Playa Sublimada", "Toalla", 28.0, 90));

        // Sublimados - Bolsos y Mochilas
        listaProductos.add(new Producto(10, "Bolso Sublimado", "Bolso", 40.0, 70));
        listaProductos.add(new Producto(11, "Mochila con Estampado Personalizado", "Mochila", 45.0, 100));
        listaProductos.add(new Producto(12, "Estuche Sublimado", "Estuche", 15.0, 80));

        // Sublimados - Tecnología
        listaProductos.add(new Producto(13, "Funda para Laptop Sublimada", "Accesorio de Laptop", 28.0, 60));
        listaProductos.add(new Producto(14, "Cargador de Teléfono con Diseño", "Cargador", 20.0, 40));
        listaProductos.add(new Producto(15, "Auriculares Personalizados", "Auriculares", 35.0, 50));

        // Sublimados - Deportes
        listaProductos.add(new Producto(16, "Balón de Fútbol Sublimado", "Balón de Fútbol", 25.0, 30));
        listaProductos.add(new Producto(17, "Toalla Deportiva Sublimada", "Toalla Deportiva", 18.0, 20));
        listaProductos.add(new Producto(18, "Botella Deportiva Personalizada", "Botella Deportiva", 12.0, 25));

        // Sublimados - Regalos
        listaProductos.add(new Producto(19, "Rompecabezas Personalizado", "Regalo Personalizado", 15.0, 15));
        listaProductos.add(new Producto(20, "Tarjeta de Felicitación Sublimada", "Tarjeta", 5.0, 50));

        // Sublimados - Artículos Escolares
        listaProductos.add(new Producto(21, "Mochila Escolar Sublimada", "Mochila Escolar", 30.0, 60));
        listaProductos.add(new Producto(22, "Estuche Escolar con Diseño", "Estuche Escolar", 10.0, 40));
        listaProductos.add(new Producto(23, "Libreta Personalizada", "Libreta", 8.0, 70));

        // Sublimados - Ropa Deportiva
        listaProductos.add(new Producto(24, "Camiseta Deportiva Sublimada", "Camiseta Deportiva", 22.0, 90));
        listaProductos.add(new Producto(25, "Shorts Deportivos con Estampado", "Shorts Deportivos", 18.0, 75));

        // Sublimados - Decoración para Eventos
        listaProductos.add(new Producto(26, "Banner Personalizado para Eventos", "Decoración para Eventos", 50.0, 10));
        listaProductos.add(new Producto(27, "Globo Sublimado con Mensaje", "Globo", 3.0, 200));

        // Sublimados - Juguetes
        listaProductos.add(new Producto(28, "Peluche Sublimado", "Peluche", 12.0, 30));
        listaProductos.add(new Producto(29, "Rompecabezas Infantil Sublimado", "Juguete Educativo", 8.0, 40));

        // Sublimados - Joyería
        listaProductos.add(new Producto(30, "Collar Personalizado", "Collar", 15.0, 50));
        listaProductos.add(new Producto(31, "Pulsera Sublimada", "Pulsera", 10.0, 60));

        // Sublimados - Artículos para Mascotas
        listaProductos.add(new Producto(32, "Collar para Mascotas con Foto", "Collar para Mascotas", 8.0, 80));
        listaProductos.add(new Producto(33, "Plato para Mascotas Sublimado", "Plato para Mascotas", 12.0, 100));

        // Sublimados - Textiles para el Hogar
        listaProductos.add(new Producto(34, "Cortina Sublimada", "Cortina", 25.0, 45));
        listaProductos.add(new Producto(35, "Almohada con Estampado Personalizado", "Almohada", 20.0, 30));

        // Sublimados - Equipos Deportivos
        listaProductos.add(new Producto(36, "Uniforme Deportivo Sublimado", "Uniforme Deportivo", 40.0, 25));
        listaProductos.add(new Producto(37, "Balón de Baloncesto Sublimado", "Balón de Baloncesto", 30.0, 20));

        // Sublimados - Artículos de Oficina
        listaProductos.add(new Producto(38, "Cuaderno de Notas con Foto", "Cuaderno de Notas", 15.0, 60));
        listaProductos.add(new Producto(39, "Porta Tarjetas Personalizado", "Accesorio de Oficina", 10.0, 80));

        // Sublimados - Artículos de Viaje
        listaProductos.add(new Producto(40, "Etiqueta para Equipaje Sublimada", "Accesorio de Viaje", 5.0, 120));
        listaProductos.add(new Producto(41, "Almohada de Viaje con Estampado", "Almohada de Viaje", 18.0, 40));

        // Sublimados - Ropa Interior
        listaProductos.add(new Producto(42, "Boxer Sublimado", "Ropa Interior", 12.0, 100));
        listaProductos.add(new Producto(43, "Sujetador Personalizado", "Ropa Interior", 15.0, 80));

        // Sublimados - Artículos de Boda
        listaProductos.add(new Producto(44, "Invitaciones de Boda Sublimadas", "Invitación de Boda", 3.0, 200));
        listaProductos.add(new Producto(45, "Vasos de Champagne Personalizados", "Vaso de Champagne", 8.0, 50));

        // Sublimados - Artículos de Camping
        listaProductos.add(new Producto(46, "Saco de Dormir Sublimado", "Saco de Dormir", 35.0, 15));
        listaProductos.add(new Producto(47, "Linterna con Diseño Personalizado", "Linterna", 10.0, 25));

        // Sublimados - Artículos para el Cuidado Personal
        listaProductos.add(new Producto(48, "Toalla Facial Sublimada", "Toalla Facial", 6.0, 150));
        listaProductos.add(new Producto(49, "Cepillo de Dientes Personalizado", "Cepillo de Dientes", 3.0, 200));

        // Sublimados - Artículos para la Playa
        listaProductos.add(new Producto(50, "Sombrilla de Playa Sublimada", "Sombrilla de Playa", 40.0, 10));

        return listaProductos;
    }
}
