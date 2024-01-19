package com.example.sub10.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.sub10.R;
import com.example.sub10.data.Modelo.Producto;

import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {
    private final Context context;
    private final List<Integer> imageResources;

    public ImagePagerAdapter(Context context, List<Integer> imageResources) {
        this.context = context;
        this.imageResources = imageResources;
    }

    @Override
    public int getCount() {
        // Devuelve un número lo suficientemente grande para lograr un bucle infinito
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        // Ajusta el tamaño de la imagen según sea necesario
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Calcula la posición real en la lista de imágenes
        int realPosition = position % imageResources.size();

        // Carga la imagen desde la carpeta de recursos
        Glide.with(context).load(imageResources.get(realPosition)).into(imageView);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

