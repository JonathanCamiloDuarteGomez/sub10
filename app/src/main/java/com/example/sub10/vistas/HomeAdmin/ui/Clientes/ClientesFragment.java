package com.example.sub10.vistas.HomeAdmin.ui.Clientes;

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
import com.example.sub10.databinding.AdminFragmentClientesBinding;

import java.util.ArrayList;
import java.util.List;

public class ClientesFragment extends Fragment {
    private ClienteAdapter clienteAdapter; // Declarar como variable miembro

    private AdminFragmentClientesBinding binding;
    private List<Cliente> listaClientes;
    private List<Cliente> listaClientesFiltrados = new ArrayList<>(); // Inicializa la lista filtrada
    private List<Cliente> listaClientesOriginal; // Copia de seguridad de la lista original


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Guardar la lista original
        listaClientesOriginal = obtenerClientesSimulados();

        binding = AdminFragmentClientesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Usar una copia de la lista original para empezar
        listaClientes = new ArrayList<>(listaClientesOriginal);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        clienteAdapter = new ClienteAdapter(listaClientes, new ClienteAdapter.OnClienteClickListener() {
            @Override
            public void onClienteClick(Cliente cliente) {
                dialogClienteInfo(cliente);
            }
        });

        recyclerView.setAdapter(clienteAdapter);

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
                buscarClientes(textoBusqueda);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAgregarCliente();
            }
        });

        return root;
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Cliente> obtenerClientesSimulados() {
        return Cliente.obtenerClientesSimulados();
    }
    private AlertDialog dialog; // Declarar como variable miembro

    public void dialogClienteInfo(Cliente cliente) {

        Activity activity = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_admin_cliente, null);

        // Configura los campos de texto en el diálogo con los datos del cliente
        TextView textViewNombre = view.findViewById(R.id.textViewNombre);
        TextView textViewTelefono = view.findViewById(R.id.textViewTelefono);
        TextView textViewCorreo = view.findViewById(R.id.textViewCorreo);
        TextView textViewCedula = view.findViewById(R.id.textViewCedula);
        TextView textViewDireccion = view.findViewById(R.id.textViewDireccion);

        textViewNombre.setText(cliente.getNombre());
        textViewTelefono.setText("Teléfono: " + cliente.getTelefono());
        textViewCorreo.setText("Correo: " + cliente.getCorreo());
        textViewCedula.setText("Cedula: "+ cliente.getCedula());
        textViewDireccion.setText("Direccion: "+cliente.getDireccion());

        // Botón "Eliminar"
        Button btnEliminarCliente = view.findViewById(R.id.btnEliminar);
        btnEliminarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaClientes.contains(cliente)) {
                    listaClientes.remove(cliente);
                    clienteAdapter.notifyDataSetChanged(); // Notifica al adaptador sobre el cambio en los datos
                }
                dialog.dismiss();
            }
        });

        // Botón "Editar"
        Button btnEditarCliente = view.findViewById(R.id.btnEditar);
        btnEditarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un nuevo cuadro de diálogo de edición
                AlertDialog.Builder editarBuilder = new AlertDialog.Builder(requireContext());
                View editView = inflater.inflate(R.layout.dialog_admin_editar_cliente, null);
                final EditText editTextNombre = editView.findViewById(R.id.editTextNombre);
                final EditText editTextTelefono = editView.findViewById(R.id.editTextTelefono);
                final EditText editTextCorreo = editView.findViewById(R.id.editTextCorreo);
                final EditText editTextCedula = editView.findViewById(R.id.editTextCedula);
                final EditText editTextDireccion = editView.findViewById(R.id.editTextDireccion);

                editTextNombre.setText(cliente.getNombre());
                editTextTelefono.setText(cliente.getTelefono());
                editTextCorreo.setText(cliente.getCorreo());
                editTextCedula.setText(cliente.getCedula());
                editTextDireccion.setText(cliente.getDireccion());

                editarBuilder.setView(editView)
                        .setTitle("Editar Cliente")
                        .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String nuevoNombre = editTextNombre.getText().toString();
                                String nuevoTelefono = editTextTelefono.getText().toString();
                                String nuevoCorreo = editTextCorreo.getText().toString();
                                String nuevaCedula = editTextCedula.getText().toString();
                                String nuevaDireccion = editTextDireccion.getText().toString();

                                // Crear una copia del cliente seleccionado
                                Cliente clienteEditado = new Cliente(
                                        cliente.getCedula(),
                                        nuevoNombre,
                                        cliente.getContraseña(),
                                        nuevoCorreo,
                                        nuevoTelefono,
                                        nuevaDireccion
                                );
                                int index = listaClientes.indexOf(cliente);
                                if (index != -1) {
                                    listaClientes.set(index, clienteEditado);
                                }

                                dialog.dismiss();
                                dialogClienteInfo(clienteEditado);

                                clienteAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancelar", null);

                AlertDialog editarDialog = editarBuilder.create();
                editarDialog.show();
            }
        });


        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }


    public void dialogAgregarCliente(){
        AlertDialog.Builder agregarBuilder = new AlertDialog.Builder(requireContext());
        View agregarView = getLayoutInflater().inflate(R.layout.dialog_admin_agregar_cliente, null);

        final EditText editTextNombre = agregarView.findViewById(R.id.editTextNombre);
        final EditText editTextTelefono = agregarView.findViewById(R.id.editTextTelefono);
        final EditText editTextCorreo = agregarView.findViewById(R.id.editTextCorreo);
        final EditText editTextCedula = agregarView.findViewById(R.id.editTextCedula);
        final EditText editTextDireccion = agregarView.findViewById(R.id.editTextDireccion);

        agregarBuilder.setView(agregarView)
                .setTitle("Agregar Cliente")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nuevoNombre = editTextNombre.getText().toString();
                        String nuevoTelefono = editTextTelefono.getText().toString();
                        String nuevoCorreo = editTextCorreo.getText().toString();
                        String nuevaCedula = editTextCedula.getText().toString();
                        String nuevaDireccion = editTextDireccion.getText().toString();

                        // Crea un nuevo cliente
                        Cliente nuevoCliente = new Cliente(nuevaCedula, nuevoNombre, "", nuevoCorreo, nuevoTelefono, nuevaDireccion);
                        listaClientes.add(nuevoCliente);
                        clienteAdapter.notifyDataSetChanged(); // Notifica al adaptador sobre el cambio en los datos
                    }
                })
                .setNegativeButton("Cancelar", null);

        AlertDialog agregarDialog = agregarBuilder.create();
        agregarDialog.show();
    }


    private void buscarClientes(String searchText) {
        listaClientesFiltrados.clear();

        if (searchText.isEmpty()) {
            // Si no hay texto de búsqueda, mostramos todos los clientes
            listaClientesFiltrados.addAll(listaClientesOriginal);
        } else {
            // Filtrar la lista original según el texto de búsqueda
            for (Cliente cliente : listaClientesOriginal) {
                if (cliente.getNombre().toLowerCase().contains(searchText.toLowerCase())) {
                    listaClientesFiltrados.add(cliente);
                }
            }
        }

        // Actualizar el RecyclerView con la lista de resultados de la búsqueda
        clienteAdapter.actualizarClientes(listaClientesFiltrados);
    }

}

class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private List<Cliente> listaClientes;
    private EditText txtBuscar;
    private OnClienteClickListener listener;

    public ClienteAdapter(List<Cliente> listaClientes, OnClienteClickListener listener) {
        this.listaClientes = listaClientes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_cliente, parent, false);
        return new ClienteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = listaClientes.get(position);
        holder.bind(cliente);
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public interface OnClienteClickListener {
        void onClienteClick(Cliente cliente);
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreTextView;
        private TextView telefonoTextView;
        private TextView correoTextView;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            telefonoTextView = itemView.findViewById(R.id.telefonoTextView);
            correoTextView = itemView.findViewById(R.id.correoTextView);
        }

        public void bind(final Cliente cliente) {
            nombreTextView.setText(cliente.getNombre());
            telefonoTextView.setText("Teléfono: " + cliente.getTelefono());
            correoTextView.setText("Correo: " + cliente.getCorreo());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClienteClick(cliente);
                }
            });
        }
    }


    public void actualizarClientes(List<Cliente> nuevosClientes) {
        listaClientes.clear();
        listaClientes.addAll(nuevosClientes);
        notifyDataSetChanged();
    }
}
