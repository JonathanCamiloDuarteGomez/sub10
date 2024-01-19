package com.example.sub10.vistas.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sub10.R;
import com.example.sub10.data.Modelo.Cliente;
import com.example.sub10.vistas.HomeAdmin.HomeAdmin;
import com.example.sub10.vistas.HomeCliente.HomeCliente;

import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private List<Cliente> listaClientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_login);

        EditText etEmail = findViewById(R.id.username);
        EditText etPassword = findViewById(R.id.password);
        Button btnInicio = findViewById(R.id.login);
        Button btnRegistrar = findViewById(R.id.registrar);


        listaClientes = Cliente.obtenerClientesSimulados();

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Los campos de inicio de sesión están vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    // Realiza la lógica para verificar el inicio de sesión con los datos simulados
                    Cliente clienteEncontrado = null;
                    for (Cliente cliente : listaClientes) {
                        if (cliente.getCorreo().equals(email) && cliente.getContraseña().equals(password)) {
                            clienteEncontrado = cliente;
                            break;
                        }
                    }

                    if (clienteEncontrado != null) {
                        if (clienteEncontrado.getCorreo().equals("admin") && clienteEncontrado.getContraseña().equals("admin")) {
                            Intent intent = new Intent(LoginActivity.this, HomeAdmin.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(LoginActivity.this, HomeCliente.class);
                            intent.putExtra("cedula", clienteEncontrado.getCedula());
                            intent.putExtra("nombre", clienteEncontrado.getNombre());
                            intent.putExtra("correo", clienteEncontrado.getCorreo());
                            intent.putExtra("telefono", clienteEncontrado.getTelefono());
                            intent.putExtra("direccion", clienteEncontrado.getDireccion());
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}