package com.example.sub10.data.Modelo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;


public class Pedido {
    private int PedidoID;
    private int ClienteID;
    private String FechaPedido;
    private String Estado;
    private String tipo;

    public Pedido(int pedidoID, int clienteID, String fechaPedido, String estado, String tipo) {
        PedidoID = pedidoID;
        ClienteID = clienteID;
        FechaPedido = fechaPedido;
        Estado = estado;
        this.tipo = tipo;
    }

    public int getPedidoID() {
        return PedidoID;
    }

    public void setPedidoID(int pedidoID) {
        PedidoID = pedidoID;
    }

    public int getClienteID() {
        return ClienteID;
    }

    public void setClienteID(int clienteID) {
        ClienteID = clienteID;
    }

    public String getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        FechaPedido = fechaPedido;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static List<Pedido> obtenerPedidosSimulados() {
        List<Pedido> listaPedidos = new ArrayList<>();

        // Cliente 1: Juan Pérez
            listaPedidos.add(new Pedido(1, 111111111, "04/12/2023", "En Proceso", "Sublimado de Gorra"));
            listaPedidos.add(new Pedido(2, 111111111, "02/12/2023", "Terminado", "Sublimado de Forro"));


        // Cliente 2: María González

            listaPedidos.add(new Pedido(3, 222222222, "04/12/2023", "En Proceso", "Sublimado de Gorra"));
            listaPedidos.add(new Pedido(4, 222222222, "06/12/2023", "En Proceso", "Sublimado de Camiseta"));


        // Cliente 3: Luis Rodríguez
            listaPedidos.add(new Pedido(5, 333333333, "04/12/2023", "En Proceso", "Sublimado de Gorra"));
            listaPedidos.add(new Pedido(6, 333333333, "08/12/2023", "Terminado", "Sublimado de Taza"));
            listaPedidos.add(new Pedido(7, 333333333, "10/12/2023", "En Proceso", "Sublimado de Gorro"));


        // Cliente 4: Carlos Sánchez

            listaPedidos.add(new Pedido(8, 444444444, "04/12/2023", "En Proceso", "Sublimado de Gorra"));


        // Cliente 5: Laura López
            listaPedidos.add(new Pedido(9, 987654321, "04/12/2023", "En Proceso", "Sublimado de Gorra"));
            listaPedidos.add(new Pedido(10, 987654321, "12/12/2023", "Terminado", "Sublimado de Sudadera"));
            listaPedidos.add(new Pedido(11, 987654321, "14/12/2023", "En Proceso", "Sublimado de Bolsa"));


        // Puedes continuar este patrón para otros clientes o ajustarlo según tus necesidades

        return listaPedidos;
    }

}
