package com.example.sub10.vistas.HomeCliente;

import android.os.Bundle;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sub10.R;

public class ProfileFragment extends Fragment {

    private ImageView profileImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;
    private TextView addressTextView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String param1;
    private String param2;

    public ProfileFragment() {
        // Required empty public constructor
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
        if (getArguments() != null) {
            param1 = getArguments().getString(ARG_PARAM1);
            param2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cliente_fragment_profile, container, false);

        // Asigna las referencias a los TextViews
        profileImageView = root.findViewById(R.id.profileImageView);
        nameTextView = root.findViewById(R.id.nameTextView);
        emailTextView = root.findViewById(R.id.emailTextView);
        phoneTextView = root.findViewById(R.id.phoneTextView);
        addressTextView = root.findViewById(R.id.addressTextView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String cedula = bundle.getString("cedula", "");
            String nombre = bundle.getString("nombre", "");
            String correo = bundle.getString("correo", "");
            String telefono = bundle.getString("telefono", "");
            String direccion = bundle.getString("direccion", "");

            nameTextView.setText(nombre);
            emailTextView.setText(correo);
            phoneTextView.setText(telefono);
            addressTextView.setText(direccion);
            // Ahora puedes usar estos datos en tu fragmento...
        }


        // Asigna los datos a los TextViews


        // Otro código del fragmento...

        return root;
    }

}