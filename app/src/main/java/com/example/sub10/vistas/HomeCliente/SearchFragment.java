package com.example.sub10.vistas.HomeCliente;

import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.sub10.R;
import com.example.sub10.data.Modelo.Cliente;
import com.example.sub10.data.Modelo.Producto;
import com.example.sub10.databinding.ClienteFragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    public SearchFragment(){}
    public String query="";
    private ClienteFragmentSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ClienteFragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUiComponents();
        setClickListeners();
    }

    private void setUiComponents() {
        // Puedes agregar configuración adicional de UI si es necesario
    }

    private void setClickListeners() {
        binding.cSearchView.etSearch.setOnEditorActionListener((textView, actionId, keyEvent) -> {

            String searchData = textView.getText().toString().trim();

            // Verifica si el texto no está vacío antes de navegar
            if (!searchData.isEmpty()) {
                // Realiza la navegación al SearchResultFragment con los datos
                Bundle bundle = new Bundle();
                bundle.putString("nameQuery", searchData);

                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_searchFragment_to_searchResultFragment, bundle);
            }

            return false;
        });
    }



}
