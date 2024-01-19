package com.example.sub10.vistas.HomeCliente;

import android.os.Bundle;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sub10.data.Modelo.Producto;
import com.example.sub10.databinding.ClienteFragmentSearchResultBinding;
import com.example.sub10.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment {
    private ClienteFragmentSearchResultBinding binding;
    private SearchFragment sf;
    String searchData;

    private ProductListAdapter adapter = new ProductListAdapter();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public SearchResultFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ClienteFragmentSearchResultBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            searchData = getArguments().getString("nameQuery");
            if (searchData != null) {
                binding.cSearchResultTopBar.tvQuery.setText(searchData);
            }
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvSearchResult.setAdapter(adapter);
        setObservers();
    }


    private void setObservers() {
        handleLoading(true); // Indicar que se está cargando

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.submitList(buscarProductos(searchData,Producto.obtenerProductosSimulados()));
                handleLoading(false); // Indicar que la carga ha finalizado
            }
        }, 2000); // Retraso de 2 segundos para simular carga
    }

    private void handleLoading(boolean isLoading) {
        binding.cSearchResultTopBar.tvResultsQty.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        binding.rvSearchResult.setVisibility(isLoading ? View.GONE : View.VISIBLE);

        View loadingScreenRoot = binding.lsLoadingScreen.getRoot();
        loadingScreenRoot.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }




    private List<Producto> buscarProductos(String txt, List<Producto> listaProductosOriginal) {

        List<Producto> filtrado = new ArrayList<>();;

        if (txt.isEmpty()) {
            // Si no hay texto de búsqueda, mostramos todos los clientes
            filtrado.addAll(listaProductosOriginal);
        } else {
            // Filtrar la lista original según el texto de búsqueda
            for (Producto producto : listaProductosOriginal) {
                if (producto.getNombre().toLowerCase().contains(txt.toLowerCase())) {
                    filtrado.add(producto);
                }
            }
        }
        binding.cSearchResultTopBar.tvResultsQty.setText(filtrado.size()+" resultados");
        return filtrado;
    }

}