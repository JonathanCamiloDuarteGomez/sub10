package com.example.sub10.vistas.HomeAdmin.ui.Productos;

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
import com.example.sub10.data.Modelo.Producto;
import com.example.sub10.databinding.AdminFragmentProductosBinding;
import java.util.ArrayList;
import java.util.List;

public class ProductosFragment extends Fragment {
    private ProductosAdapter productosAdapter;
    private AdminFragmentProductosBinding binding;
    private List<Producto> listaProductos;
    private List<Producto> listaProductosFiltrados = new ArrayList<>();
    private List<Producto> listaProductosOriginal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = AdminFragmentProductosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtén la lista de productos simulados
        listaProductosOriginal = obtenerProductosSimulados();

        // Inicializa la lista original con la lista de productos
        listaProductos = new ArrayList<>(listaProductosOriginal);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        productosAdapter = new ProductosAdapter(listaProductos, new ProductosAdapter.OnProductoClickListener() {
            @Override
            public void onProductoClick(Producto producto) {
                dialogProductoInfo(producto);
            }
        });

        recyclerView.setAdapter(productosAdapter);

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
                buscarProductos(textoBusqueda);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAgregarProducto();
            }
        });

        return root;
    }


    private List<Producto> obtenerProductosSimulados() {
        // Lógica para obtener la lista de productos simulados
        return Producto.obtenerProductosSimulados();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private AlertDialog dialog;

    public void dialogProductoInfo(Producto producto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_admin_producto, null);

        // Configura los campos de texto en el diálogo con los datos del producto
        TextView textViewNombre = view.findViewById(R.id.textViewNombreProducto);
        TextView textViewPrecio = view.findViewById(R.id.textViewPrecioProducto);
        TextView textViewStock = view.findViewById(R.id.textViewStockProducto);

        textViewNombre.setText(producto.getNombre());
        textViewPrecio.setText("Precio: " + producto.getPrecio());
        textViewStock.setText("Stock: " + producto.getStock());

        // Botón "Eliminar"
        Button btnEliminarProducto = view.findViewById(R.id.btnEliminarProducto);
        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaProductos.contains(producto)) {
                    listaProductos.remove(producto);
                    productosAdapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });

        // Botón "Editar"
        Button btnEditarProducto = view.findViewById(R.id.btnEditarProducto);
        btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un nuevo cuadro de diálogo de edición
                AlertDialog.Builder editarBuilder = new AlertDialog.Builder(requireContext());
                View editView = inflater.inflate(R.layout.dialog_admin_editar_producto, null);
                final EditText editTextNombre = editView.findViewById(R.id.editTextNombre);
                final EditText editTextDescripcion = editView.findViewById(R.id.editTextDesc);
                final EditText editTextPrecio = editView.findViewById(R.id.editTextPrecio);
                final EditText editTextStock = editView.findViewById(R.id.editTextStock);

                editTextNombre.setText(producto.getNombre());
                editTextDescripcion.setText(producto.getDescripcion());
                editTextPrecio.setText(producto.getPrecio()+"");
                editTextStock.setText(producto.getStock()+"");

                editarBuilder.setView(editView)
                        .setTitle("Editar Producto")
                        .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String nuevoNombre = editTextNombre.getText().toString();
                                String nuevoDescripcion = editTextDescripcion.getText().toString();
                                String nuevoPrecio = editTextPrecio.getText().toString();
                                String nuevaStock = editTextStock.getText().toString();

                                // Crear una copia del cliente seleccionado
                                Producto ProductoEditado = new Producto(
                                        producto.getId(),
                                        nuevoNombre,
                                        nuevoDescripcion,
                                        Double.parseDouble(nuevoPrecio),
                                        Integer.parseInt(nuevaStock)
                                );
                                int index = listaProductos.indexOf(producto);
                                if (index != -1) {
                                    listaProductos.set(index, ProductoEditado);
                                }

                                dialog.dismiss();
                                dialogProductoInfo(ProductoEditado);

                                productosAdapter.notifyDataSetChanged();
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

    public void dialogAgregarProducto() {
        AlertDialog.Builder agregarBuilder = new AlertDialog.Builder(requireContext());
        View agregarView = getLayoutInflater().inflate(R.layout.dialog_admin_agregar_producto, null);

        final EditText editTextId = agregarView.findViewById(R.id.editTextId);
        final EditText editTextNombre = agregarView.findViewById(R.id.editTextNombre);
        final EditText editTextCategoria = agregarView.findViewById(R.id.editTextCategoria);
        final EditText editTextPrecio = agregarView.findViewById(R.id.editTextPrecio);
        final EditText editTextStock = agregarView.findViewById(R.id.editTextStock);

        agregarBuilder.setView(agregarView)
                .setTitle("Agregar Producto")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String id = editTextId.getText().toString();
                        String nombre = editTextNombre.getText().toString();
                        String categoria = editTextCategoria.getText().toString();
                        String precio = editTextPrecio.getText().toString();
                        String stock = editTextStock.getText().toString();

                        //
                        Producto nuevoProducto = new Producto(Integer.parseInt(id), nombre, categoria, Double.parseDouble(precio), Integer.parseInt(stock));
                        listaProductos.add(nuevoProducto);
                        productosAdapter.notifyDataSetChanged(); // Notifica al adaptador sobre el cambio en los datos
                    }
                })
                .setNegativeButton("Cancelar", null);

        AlertDialog agregarDialog = agregarBuilder.create();
        agregarDialog.show();
    }

    private void buscarProductos(String searchText) {
        listaProductosFiltrados.clear();

        if (searchText.isEmpty()) {
            // Si no hay texto de búsqueda, mostramos todos los productos
            listaProductosFiltrados.addAll(listaProductosOriginal);
        } else {
            // Filtrar la lista original según el texto de búsqueda
            for (Producto producto : listaProductosOriginal) {
                if (producto.getNombre().toLowerCase().contains(searchText.toLowerCase())) {
                    listaProductosFiltrados.add(producto);
                }
            }
        }

        // Actualizar el RecyclerView con la lista de resultados de la búsqueda
        productosAdapter.actualizarProductos(listaProductosFiltrados);
    }
}

class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder> {
    private List<Producto> listaProductos;
    private OnProductoClickListener listener;

    public ProductosAdapter(List<Producto> listaProductos, OnProductoClickListener listener) {
        this.listaProductos = listaProductos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_producto, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public interface OnProductoClickListener {
        void onProductoClick(Producto producto);
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreTextView;
        private TextView precioTextView;
        private TextView stockTextView;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            precioTextView = itemView.findViewById(R.id.precioTextView);
            stockTextView = itemView.findViewById(R.id.stockTextView);
        }

        public void bind(final Producto producto) {
            nombreTextView.setText(producto.getNombre());
            precioTextView.setText("Precio: " + producto.getPrecio());
            stockTextView.setText("Stock: " + producto.getStock());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onProductoClick(producto);
                }
            });
        }
    }

    public void actualizarProductos(List<Producto> nuevosProductos) {
        listaProductos.clear();
        listaProductos.addAll(nuevosProductos);
        notifyDataSetChanged();
    }
}
