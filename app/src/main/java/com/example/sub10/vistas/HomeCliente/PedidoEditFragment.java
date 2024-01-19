package com.example.sub10.vistas.HomeCliente;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.example.sub10.R;
import com.example.sub10.databinding.ClienteFragmentEditarPedidoBinding;


public class PedidoEditFragment extends Fragment {
    Spinner spinnerTipoProducto;
    ImageView img;

    View verde;
    View azul;
    View rojo;
    View gris;

    Button btnCargarImagen;
    ImageView imageViewSeleccionada;

    private ClienteFragmentEditarPedidoBinding binding;
    String searchData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ClienteFragmentEditarPedidoBinding.inflate(inflater, container, false);

        img=binding.getRoot().findViewById(R.id.imageViewProducto);
        verde=binding.getRoot().findViewById(R.id.cVerde);
        azul=binding.getRoot().findViewById(R.id.cAzul);
        rojo=binding.getRoot().findViewById(R.id.cRojo);
        gris=binding.getRoot().findViewById(R.id.cGris);
        btnCargarImagen = binding.getRoot().findViewById(R.id.btnCargarImagen);
        imageViewSeleccionada = binding.getRoot().findViewById(R.id.imageViewSeleccionada);

        spinnerTipoProducto = binding.getRoot().findViewById(R.id.spinnerTipoProducto);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.opciones_spinner_productos,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoProducto.setAdapter(adapter);


        return binding.getRoot();
    }

    String item="";
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerTipoProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch(position){
                    case 0:
                        verde.setBackgroundResource(R.mipmap.color_verde);
                        azul.setBackgroundResource(R.mipmap.color_azul);
                        rojo.setBackgroundResource(R.mipmap.color_rojo);
                        gris.setBackgroundResource(R.mipmap.color_gris);
                        img.setImageResource(R.mipmap.ic_out_producto);
                        item="";
                        break;
                    case 1:
                        verde.setBackgroundResource(R.mipmap.color_verde);
                        azul.setBackgroundResource(R.mipmap.color_azul);
                        rojo.setBackgroundResource(R.mipmap.color_rojo);
                        gris.setBackgroundResource(R.mipmap.color_gris);
                        img.setImageResource(R.mipmap.camiseta_blanca);
                        item="camiseta";
                        break;
                    case 2:
                        verde.setBackgroundResource(R.mipmap.color_verde);
                        azul.setBackgroundResource(R.mipmap.color_azul);
                        rojo.setBackgroundResource(R.mipmap.color_rojo);
                        gris.setBackgroundResource(R.mipmap.color_gris);
                        img.setImageResource(R.mipmap.taza_blanca);
                        item="taza";
                        break;
                    case 3:
                        verde.setBackgroundResource(R.mipmap.color_verde);
                        azul.setBackgroundResource(R.mipmap.color_azul);
                        rojo.setBackgroundResource(R.mipmap.color_rojo);
                        gris.setBackgroundResource(R.mipmap.color_gris);
                        img.setImageResource(R.mipmap.gorra_blanca);
                        item="gorra";
                        break;
                    case 4:
                        verde.setBackgroundResource(R.mipmap.color_verde);
                        azul.setBackgroundResource(R.mipmap.color_azul);
                        rojo.setBackgroundResource(R.mipmap.color_rojo);
                        gris.setBackgroundResource(R.mipmap.color_gris);
                        img.setImageResource(R.mipmap.mochila_blanca);
                        item="mochila";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No es necesario hacer algo aquí en este caso
            }
        });

        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verde.setBackgroundResource(R.mipmap.color_verde_select);
                azul.setBackgroundResource(R.mipmap.color_azul);
                rojo.setBackgroundResource(R.mipmap.color_rojo);
                gris.setBackgroundResource(R.mipmap.color_gris);
                switch (item){
                    case "camiseta":
                        img.setImageResource(R.mipmap.camiseta_verde);
                        break;
                    case "taza":
                        img.setImageResource(R.mipmap.taza_verde);
                        break;
                    case "gorra":
                        img.setImageResource(R.mipmap.gorra_verde);
                        break;
                    case "mochila":
                        img.setImageResource(R.mipmap.mochila_verde);
                        break;

                }
            }
        });

        // Cambia a azul
        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verde.setBackgroundResource(R.mipmap.color_verde);
                azul.setBackgroundResource(R.mipmap.color_azul_select);
                rojo.setBackgroundResource(R.mipmap.color_rojo);
                gris.setBackgroundResource(R.mipmap.color_gris);
                switch (item) {
                    case "camiseta":
                        img.setImageResource(R.mipmap.camiseta_azul);
                        break;
                    case "taza":
                        img.setImageResource(R.mipmap.taza_azul);
                        break;
                    case "gorra":
                        img.setImageResource(R.mipmap.gorra_azul);
                        break;
                    case "mochila":
                        img.setImageResource(R.mipmap.mochila_azul);
                        break;
                    // Agrega más casos si es necesario...
                }
            }
        });

        // cambia a "rojo"
        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verde.setBackgroundResource(R.mipmap.color_verde);
                azul.setBackgroundResource(R.mipmap.color_azul);
                rojo.setBackgroundResource(R.mipmap.color_rojo_select);
                gris.setBackgroundResource(R.mipmap.color_gris);
                switch (item) {
                    case "camiseta":
                        img.setImageResource(R.mipmap.camiseta_roja);
                        break;
                    case "taza":
                        img.setImageResource(R.mipmap.taza_roja);
                        break;
                    case "gorra":
                        img.setImageResource(R.mipmap.gorra_roja);
                        break;
                    case "mochila":
                        img.setImageResource(R.mipmap.mochila_roja);
                        break;
                    // Agrega más casos si es necesario...
                }
            }
        });

        // cambia a "gris"
        gris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verde.setBackgroundResource(R.mipmap.color_verde);
                azul.setBackgroundResource(R.mipmap.color_azul);
                rojo.setBackgroundResource(R.mipmap.color_rojo);
                gris.setBackgroundResource(R.mipmap.color_gris_select);
                switch (item) {
                    case "camiseta":
                        img.setImageResource(R.mipmap.camiseta_gris);
                        break;
                    case "taza":
                        img.setImageResource(R.mipmap.taza_gris);
                        break;
                    case "gorra":
                        img.setImageResource(R.mipmap.gorra_gris);
                        break;
                    case "mochila":
                        img.setImageResource(R.mipmap.mochila_gris);
                        break;
                    // Agrega más casos si es necesario...
                }
            }
        });



        btnCargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 123);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                imageViewSeleccionada.setImageURI(selectedImageUri);
            }
        }
    }
}