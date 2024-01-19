package com.example.sub10.vistas.HomeCliente;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.example.sub10.R;
import com.example.sub10.data.ImagePagerAdapter;
import com.example.sub10.data.Modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String param1;
    private String param2;

    private ViewPager viewPager;
    private ImagePagerAdapter imagePagerAdapter;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 0;
    private final long PERIOD_MS = 2000;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cliente_fragment_home, container, false);

        ImageView ivAnuncio1 = root.findViewById(R.id.item1).findViewById(R.id.ivImage);

        ImageView ivAnuncio2 = root.findViewById(R.id.item2).findViewById(R.id.ivImage);

        ImageView ivAnuncio3 = root.findViewById(R.id.item3).findViewById(R.id.ivImage);

        ImageView ivAnuncio4 = root.findViewById(R.id.item4).findViewById(R.id.ivImage);

        ivAnuncio1.setImageResource(R.mipmap.gf_camiseta);
        ivAnuncio2.setImageResource(R.mipmap.gf_taza);
        ivAnuncio3.setImageResource(R.mipmap.gf_impresora);
        ivAnuncio4.setImageResource(R.mipmap.gf_burbuja);
/*
        ivAnuncio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchData = "Camiseta";
                    Bundle bundle = new Bundle();
                    bundle.putString("nameQuery", searchData);
                    NavController navController = Navigation.findNavController(requireView());
                    navController.navigate(R.id.action_searchFragment_to_searchResultFragment, bundle);
                }
            });

        ivAnuncio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchData = "Taza";
                Bundle bundle = new Bundle();
                bundle.putString("nameQuery", searchData);
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_searchFragment_to_searchResultFragment, bundle);
            }
        });
*/
        viewPager = root.findViewById(R.id.viewPager);

        // Configura el adaptador con un conjunto de imágenes actualizado
        imagePagerAdapter = new ImagePagerAdapter(getContext(),getUpdatedImages());
        viewPager.setAdapter(imagePagerAdapter);

        final Handler handler = new Handler(Looper.myLooper());
        final Runnable updateRunnable = new Runnable() {
            public void run() {
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(updateRunnable);
            }
        }, DELAY_MS, PERIOD_MS);





        return root;
    }

    // Método para obtener un conjunto de imágenes actualizado
    private List<Integer> getUpdatedImages() {
        // Aquí puedes proporcionar el conjunto actualizado de imágenes
        List<Integer> updatedImages = new ArrayList<>();
        updatedImages.add(R.mipmap.banner1);
        updatedImages.add(R.mipmap.banner2);
        updatedImages.add(R.mipmap.banner3);
        updatedImages.add(R.mipmap.banner4);
        updatedImages.add(R.mipmap.banner5);
        // ... Agrega más imágenes según sea necesario
        return updatedImages;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}