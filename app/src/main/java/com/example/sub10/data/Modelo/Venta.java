package com.example.sub10.data.Modelo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;


public class Venta {

    private int VentaID;
    private int PedidoID;
    private int ProductoID;
    private int Cantidad;
    private double PrecioUnitario;
    private double ImporteTotal;

    public Venta() {
    }

    public Venta(int ventaID, int pedidoID, int productoID, int cantidad, double precioUnitario, double importeTotal) {
        VentaID = ventaID;
        PedidoID = pedidoID;
        ProductoID = productoID;
        Cantidad = cantidad;
        PrecioUnitario = precioUnitario;
        ImporteTotal = importeTotal;
    }


    public int getVentaID() {
        return VentaID;
    }

    public void setVentaID(int ventaID) {
        VentaID = ventaID;
    }

    public int getPedidoID() {
        return PedidoID;
    }

    public void setPedidoID(int pedidoID) {
        PedidoID = pedidoID;
    }

    public int getProductoID() {
        return ProductoID;
    }

    public void setProductoID(int productoID) {
        ProductoID = productoID;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        PrecioUnitario = precioUnitario;
    }

    public double getImporteTotal() {
        return ImporteTotal;
    }

    public void setImporteTotal(double importeTotal) {
        ImporteTotal = importeTotal;
    }


    public static List<Venta> obtenerVentasSimuladas() {
        List<Venta> listaVentas = new ArrayList<>();


        listaVentas.add(new Venta(1, 2, 0, 5, 40000.00, 40000.00 * 5));
        listaVentas.add(new Venta(2, 6, 0, 3, 50000.00, 50000.00 * 3));
        listaVentas.add(new Venta(3, 10, 0, 1, 30000.00, 30000.00));
        listaVentas.add(new Venta(5, 0, 1, 3, 25000.00, 25000.00 * 3));
        listaVentas.add(new Venta(6, 0, 4, 4, 20000.00, 20000.00 * 4));
        listaVentas.add(new Venta(7, 0, 5, 4, 18000.00, 18000.00 * 4));
        listaVentas.add(new Venta(8, 0, 6, 9, 12000.00, 12000.00 * 9));
        return listaVentas;
    }
}