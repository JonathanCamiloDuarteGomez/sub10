package com.example.sub10.vistas.HomeCliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.example.sub10.R;
import com.example.sub10.databinding.ClienteFragmentCartBinding;

    public class CartFragment extends Fragment {

        ClienteFragmentCartBinding binding;


        public CartFragment() {
        }

        public static CartFragment newInstance(String param1, String param2) {
            CartFragment fragment = new CartFragment();
            Bundle args = new Bundle();

            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            binding = ClienteFragmentCartBinding.inflate(inflater, container, false);
            return  binding .getRoot();
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            setClickListeners();
        }
        private void setClickListeners() {
            // Obtener el NavController desde la vista
            NavController navController = Navigation.findNavController(requireView());

            // Agregar un OnClickListener al botón btnRealizarPedido
            binding.btnRealizarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Navegar al fragmento deseado al pulsar el botón
                    navController.navigate(R.id.action_cartFragment_to_pedidoEditFragment);
                }
            });
        }


    }