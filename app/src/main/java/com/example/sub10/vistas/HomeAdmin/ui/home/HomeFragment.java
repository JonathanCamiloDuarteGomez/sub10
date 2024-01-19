package com.example.sub10.vistas.HomeAdmin.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sub10.R;
import com.example.sub10.data.Modelo.Producto;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;





import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private BarChart barChart;
    private PieChart pieChart;
    private LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.admin_fragment_home, container, false);

        // Inicializa tus gráficos
        barChart = rootView.findViewById(R.id.barChart);
        pieChart = rootView.findViewById(R.id.pieChart);
        lineChart = rootView.findViewById(R.id.lineChart);

        // Configura y muestra los gráficos
        showBarChart();
        showPieChart();
        showLineChart();

        return rootView;
    }

    private void showBarChart() {
        BarData barData = generateBarData();
        barChart.setData(barData);

        // Configuraciones adicionales para el gráfico de barras
        // ...

        barChart.invalidate(); // Actualiza el gráfico
    }

    private void showPieChart() {
        PieData pieData = generatePieData();
        pieChart.setData(pieData);

        // Configuraciones adicionales para el gráfico de pie
        // ...

        pieChart.invalidate(); // Actualiza el gráfico
    }

    private void showLineChart() {
        LineData lineData = generateLineData();
        lineChart.setData(lineData);

        // Configuraciones adicionales para el gráfico de líneas
        // ...

        lineChart.invalidate(); // Actualiza el gráfico
    }

    private BarData generateBarData() {
        List<BarEntry> barEntries = new ArrayList<>();

        // Genera datos aleatorios de ventas por producto
        for (int i = 0; i < 10; i++) { // Supongamos que tienes 10 productos
            float valor = (float) (Math.random() * 1000); // Datos de ventas aleatorios
            barEntries.add(new BarEntry(i, valor));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Ventas por producto");
        BarData barData = new BarData(barDataSet);

        // Personaliza el gráfico de barras
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(12f);
        barDataSet.setValueTextColor(Color.BLACK);

        return barData;
    }

    private PieData generatePieData() {
        List<PieEntry> pieEntries = new ArrayList<>();

        // Genera datos aleatorios de ventas por categoría
        String[] categorias = new String[]{"Categoría A", "Categoría B", "Categoría C", "Categoría D", "Categoría E"};
        for (int i = 0; i < categorias.length; i++) {
            float valor = (float) (Math.random() * 1000); // Datos de ventas aleatorios
            pieEntries.add(new PieEntry(valor, categorias[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Ventas por categoría");
        return new PieData(pieDataSet);
    }

    private LineData generateLineData() {
        List<Entry> lineEntries = new ArrayList<>();

        // Genera datos aleatorios de ventas a lo largo del tiempo
        for (int i = 0; i < 12; i++) { // Supongamos 12 meses
            float valor = (float) (Math.random() * 1000); // Datos de ventas aleatorios
            lineEntries.add(new Entry(i, valor));
        }

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Ventas a lo largo del tiempo");
        return new LineData(lineDataSet);
    }
}