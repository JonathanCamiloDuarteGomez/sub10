package com.example.sub10.vistas.HomeAdmin.ui.Ventas;

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
import com.example.sub10.data.Modelo.Producto;
import com.example.sub10.data.Modelo.Venta;
import com.example.sub10.databinding.AdminFragmentProductosBinding;
import com.example.sub10.databinding.AdminFragmentVentasBinding;

import java.util.ArrayList;
import java.util.List;

public class VentasFragment extends Fragment {
    private VentaAdapter ventaAdapter;
    private AdminFragmentVentasBinding binding;
    private List<Venta> listaVentas;
    private List<Venta> listaVentasFiltradas = new ArrayList<>();
    private List<Venta> listaVentasOriginal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = AdminFragmentVentasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtén la lista de ventas simuladas
        listaVentasOriginal = obtenerVentasSimuladas();

        // Inicializa la lista original con la lista de ventas
        listaVentas = new ArrayList<>(listaVentasOriginal);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewVentas);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        ventaAdapter = new VentaAdapter(listaVentas, new VentaAdapter.OnVentaClickListener() {
            @Override
            public void onVentaClick(Venta venta) {
                dialogVentaInfo(venta);
            }
        });

        recyclerView.setAdapter(ventaAdapter);

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
                buscarVentas(textoBusqueda);
            }
        });

        return root;
    }

    private List<Venta> obtenerVentasSimuladas() {
        // Lógica para obtener la lista de ventas simuladas
        return Venta.obtenerVentasSimuladas();
    }

    private AlertDialog dialog;

    public void dialogVentaInfo(Venta venta) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_admin_venta, null);

        // Configura los campos de texto en el diálogo con los datos de la venta
        TextView textViewPedidoID = view.findViewById(R.id.textViewPedidoID);
        TextView textViewProductoID = view.findViewById(R.id.textViewProductoID);
        TextView textViewCantidad = view.findViewById(R.id.textViewCantidad);
        TextView textViewPrecioUnitario = view.findViewById(R.id.textViewPrecioUnitario);
        TextView textViewImporteTotal = view.findViewById(R.id.textViewImporteTotal);

        textViewPedidoID.setText("Pedido ID: " + venta.getPedidoID());
        textViewProductoID.setText("Producto ID: " + venta.getProductoID());
        textViewCantidad.setText("Cantidad: " + venta.getCantidad());
        textViewPrecioUnitario.setText("Precio Unitario: " + venta.getPrecioUnitario());
        textViewImporteTotal.setText("Importe Total: " + venta.getImporteTotal());

        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    private void buscarVentas(String searchText) {
        listaVentasFiltradas.clear();

        if (searchText.isEmpty()) {
            // Si no hay texto de búsqueda, mostramos todas las ventas
            listaVentasFiltradas.addAll(listaVentasOriginal);
        } else {
            // Filtrar la lista original según el texto de búsqueda
            for (Venta venta : listaVentasOriginal) {
                // Puedes ajustar las condiciones de búsqueda según tus necesidades
                if (String.valueOf(venta.getPedidoID()).contains(searchText) ||
                        String.valueOf(venta.getProductoID()).contains(searchText) ||
                        String.valueOf(venta.getCantidad()).contains(searchText) ||
                        String.valueOf(venta.getPrecioUnitario()).contains(searchText) ||
                        String.valueOf(venta.getImporteTotal()).contains(searchText)) {
                    listaVentasFiltradas.add(venta);
                }
            }
        }

        // Actualizar el RecyclerView con la lista de resultados de la búsqueda
        ventaAdapter.actualizarVentas(listaVentasFiltradas);
    }
}


class VentaAdapter extends RecyclerView.Adapter<VentaAdapter.VentaViewHolder> {

    private List<Venta> listaVentas;
    private OnVentaClickListener listener;

    public VentaAdapter(List<Venta> listaVentas, OnVentaClickListener listener) {
        this.listaVentas = listaVentas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_venta, parent, false);
        return new VentaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VentaViewHolder holder, int position) {
        Venta venta = listaVentas.get(position);
        holder.bind(venta);
    }

    @Override
    public int getItemCount() {
        return listaVentas.size();
    }

    public interface OnVentaClickListener {
        void onVentaClick(Venta venta);
    }

    public class VentaViewHolder extends RecyclerView.ViewHolder {
        private TextView pedidoIdTextView;
        private TextView productoIdTextView;
        private TextView cantidadTextView;
        private TextView precioUnitarioTextView;
        private TextView importeTotalTextView;

        public VentaViewHolder(@NonNull View itemView) {
            super(itemView);
            precioUnitarioTextView = itemView.findViewById(R.id.unitarioTextView);
            cantidadTextView = itemView.findViewById(R.id.cantidadTextView);
            importeTotalTextView = itemView.findViewById(R.id.totalTextView);
        }

        public void bind(final Venta venta) {
            cantidadTextView.setText("Cantidad: " + venta.getCantidad());
            precioUnitarioTextView.setText("Precio Unitario: " + venta.getPrecioUnitario());
            importeTotalTextView.setText("Importe Total: " + venta.getImporteTotal());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onVentaClick(venta);
                }
            });
        }
    }

    public void actualizarVentas(List<Venta> nuevasVentas) {
        listaVentas.clear();
        listaVentas.addAll(nuevasVentas);
        notifyDataSetChanged();
    }
}
