package com.example.sub10.vistas.HomeCliente;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.sub10.R;
import com.example.sub10.databinding.VistaHomeClienteBinding;

public class HomeCliente extends AppCompatActivity {

    private VistaHomeClienteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtén los datos
        String cedula = getIntent().getStringExtra("cedula");
        String nombre = getIntent().getStringExtra("nombre");
        String correo = getIntent().getStringExtra("correo");
        String telefono = getIntent().getStringExtra("telefono");
        String direccion = getIntent().getStringExtra("direccion");

        // Crea un nuevo fragmento ProfileFragment
        ProfileFragment profileFragment = new ProfileFragment();

        // Establece los argumentos (datos) para el fragmento
        Bundle bundle = new Bundle();
        bundle.putString("cedula", cedula);
        bundle.putString("nombre", nombre);
        bundle.putString("correo", correo);
        bundle.putString("telefono", telefono);
        bundle.putString("direccion", direccion);

        profileFragment.setArguments(bundle);

        binding = VistaHomeClienteBinding.inflate(getLayoutInflater());
        Log.d("Tag",""+binding);
        setContentView(binding.getRoot());
        Log.d("Tag",""+binding.getRoot());

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fMain);
        NavController navController = navHostFragment.getNavController();

        binding.bnvMainMenu.setOnItemSelectedListener(item -> {
            NavigationUI.onNavDestinationSelected(item, navController);
            return true;
        });

        binding.bnvMainMenu.setOnItemReselectedListener(item -> {
            NavGraph selectedMenuItemNavGraph =
                    (NavGraph) navController.getGraph().findNode(item.getItemId());
            if (selectedMenuItemNavGraph != null) {
                navController.popBackStack(selectedMenuItemNavGraph.getStartDestination(), false);
            }
        });
        NavigationUI.setupWithNavController(binding.bnvMainMenu, navController);
    }
}
