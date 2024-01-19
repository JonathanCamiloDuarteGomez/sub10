package com.example.sub10.vistas.HomeAdmin.ui.Pedidos;

import static com.example.sub10.data.Modelo.Pedido.obtenerPedidosSimulados;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sub10.R;
import com.example.sub10.data.Modelo.Cliente;
import com.example.sub10.data.Modelo.Pedido;
import com.example.sub10.databinding.AdminFragmentPedidosBinding;

import java.util.ArrayList;
import java.util.List;

public class PedidosFragment extends Fragment {
    private PedidoAdapter pedidoAdapter;
    private AdminFragmentPedidosBinding binding;
    private List<Pedido> listaPedidos;
    private List<Pedido> listaPedidosFiltrados = new ArrayList<>();
    private List<Pedido> listaPedidosOriginal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = AdminFragmentPedidosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtén la lista de pedidos simulados
        listaPedidosOriginal = obtenerPedidosSimulados();

        // Inicializa la lista original con la lista de pedidos
        listaPedidos = new ArrayList<>(listaPedidosOriginal);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        pedidoAdapter = new PedidoAdapter(listaPedidos, new PedidoAdapter.OnPedidoClickListener() {
            @Override
            public void onPedidoClick(Pedido pedido) {
                dialogPedidoInfo(pedido);
            }
        });

        recyclerView.setAdapter(pedidoAdapter);

        EditText txtBuscar = root.findViewById(R.id.txtBuscar);
        Button btnAgregar = root.findViewById(R.id.btnAgregar);

        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String textoBusqueda = editable.toString();
                buscarPedidos(textoBusqueda);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAgregarPedido();
            }
        });

        return root;
    }

    private List<Pedido> obtenerPedidosSimulados() {
        // Lógica para obtener la lista de pedidos simulados
        return Pedido.obtenerPedidosSimulados();
    }

    private AlertDialog dialog;

    public void dialogPedidoInfo(Pedido pedido) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_admin_pedido, null);

        // Configura los campos de texto en el diálogo con los datos del pedido
        TextView textViewFecha = view.findViewById(R.id.textViewFecha);
        TextView textViewEstado = view.findViewById(R.id.textViewEstado);
        TextView textViewTipo = view.findViewById(R.id.textViewTipo);
        TextView textViewCedulaCliente = view.findViewById(R.id.textViewCedulaCliente);

        Cliente cliente = obtenerClientePorID(pedido.getClienteID());

        if (obtenerClientePorID(pedido.getClienteID()) != null) {

            TextView textViewNombreCliente = view.findViewById(R.id.textViewNombreCliente);
            TextView textViewCorreoCliente = view.findViewById(R.id.textViewCorreoCliente);
            TextView textViewTelefonoCliente = view.findViewById(R.id.textViewTelefonoCliente);
            TextView textViewDireccionCliente = view.findViewById(R.id.textViewDireccionCliente);


            textViewNombreCliente.setText("Nombre del Cliente: " + cliente.getNombre());
            textViewCorreoCliente.setText("Correo del Cliente: " + cliente.getCorreo());
            textViewTelefonoCliente.setText("Teléfono del Cliente: " + cliente.getTelefono());
            textViewDireccionCliente.setText("Dirección del Cliente: " + cliente.getDireccion());

        }
        textViewFecha.setText("Fecha: " + pedido.getFechaPedido());
        textViewEstado.setText("Estado: " + pedido.getEstado());
        textViewTipo.setText("Tipo: " + pedido.getTipo());
        textViewCedulaCliente.setText("Cedula del Cliente: " + pedido.getClienteID());


        // Botón "Eliminar"
        Button btnEliminarPedido = view.findViewById(R.id.btnEliminarPedido);
        btnEliminarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaPedidos.contains(pedido)) {
                    listaPedidos.remove(pedido);
                    pedidoAdapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });

        // Botón "Editar"
        Button btnEditarPedido = view.findViewById(R.id.btnEditarPedido);
        btnEditarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementa la lógica de edición del pedido aquí
                // Similar a la lógica que tienes para editar clientes
                // ...

                // Una vez editado, actualiza la lista y notifica al adaptador
                pedidoAdapter.notifyDataSetChanged();

                dialog.dismiss(); // Cerrar el diálogo después de editar
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    public Cliente obtenerClientePorID(int clienteID) {
        for (Cliente cliente : obtenerClientesSimulados()) {
            if (Integer.parseInt(cliente.getCedula()) == clienteID) {
                return cliente;
            }
        }
        return null;  // Retorna null si no se encuentra el cliente con el ID especificado
    }
    private List<Cliente> obtenerClientesSimulados() {
        return Cliente.obtenerClientesSimulados();
    }

    public void dialogAgregarPedido() {
        // Implementa la lógica para agregar un nuevo pedido aquí
        // Similar a la lógica que tienes para agregar clientes
        // ...

        // Una vez agregado, actualiza la lista y notifica al adaptador
        pedidoAdapter.notifyDataSetChanged();

        // Cierra el diálogo después de agregar
        dialog.dismiss();
    }

    private void buscarPedidos(String searchText) {
        listaPedidosFiltrados.clear();

        if (searchText.isEmpty()) {
            // Si no hay texto de búsqueda, mostramos todos los pedidos
            listaPedidosFiltrados.addAll(listaPedidosOriginal);
        } else {
            // Filtrar la lista original según el texto de búsqueda
            for (Pedido pedido : listaPedidosOriginal) {
                // Puedes ajustar las condiciones de búsqueda según tus necesidades
                if (pedido.getFechaPedido().toLowerCase().contains(searchText.toLowerCase()) ||
                        pedido.getEstado().toLowerCase().contains(searchText.toLowerCase()) ||
                        pedido.getTipo().toLowerCase().contains(searchText.toLowerCase())) {
                    listaPedidosFiltrados.add(pedido);
                }
            }
        }

        // Actualizar el RecyclerView con la lista de resultados de la búsqueda
        pedidoAdapter.actualizarPedidos(listaPedidosFiltrados);
    }
}


class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private List<Pedido> listaPedidos;
    private OnPedidoClickListener listener;

    public PedidoAdapter(List<Pedido> listaPedidos, OnPedidoClickListener listener) {
        this.listaPedidos = listaPedidos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_pedido, parent, false);
        return new PedidoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = listaPedidos.get(position);
        holder.bind(pedido);
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public interface OnPedidoClickListener {
        void onPedidoClick(Pedido pedido);
    }

    public class PedidoViewHolder extends RecyclerView.ViewHolder {
        private TextView fechaTextView;
        private TextView estadoTextView;
        private TextView tipoTextView;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
            estadoTextView = itemView.findViewById(R.id.estadoTextView);
            tipoTextView = itemView.findViewById(R.id.tipoTextView);
        }

        public void bind(final Pedido pedido) {
            fechaTextView.setText("Fecha: " + pedido.getFechaPedido());
            estadoTextView.setText("Estado: " + pedido.getEstado());
            tipoTextView.setText("Tipo: " + pedido.getTipo());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPedidoClick(pedido);
                }
            });
        }
    }

    public void actualizarPedidos(List<Pedido> nuevosPedidos) {
        listaPedidos.clear();
        listaPedidos.addAll(nuevosPedidos);
        notifyDataSetChanged();
    }
}