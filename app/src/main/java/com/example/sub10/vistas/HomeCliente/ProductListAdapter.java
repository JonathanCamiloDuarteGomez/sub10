package com.example.sub10.vistas.HomeCliente;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sub10.R;
import com.example.sub10.data.Modelo.Producto;
import com.example.sub10.databinding.ItemProductBinding;


public class ProductListAdapter extends ListAdapter<Producto, ProductListAdapter.ProductoViewHolder> {

    private ProductClickListener productClickListener;

    public ProductListAdapter() {
        super(new ProductoDiffCallback());
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProductBinding binding = ItemProductBinding.inflate(inflater, parent, false);
        return new ProductoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = getItem(position);
        holder.bind(producto);
    }

    public void setProductClickListener(ProductClickListener listener) {
        this.productClickListener = listener;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder {

        private final ItemProductBinding binding;

        public ProductoViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && productClickListener != null) {
                    productClickListener.onProductClick(getItem(position));
                }
            });
        }

        public void bind(Producto producto) {
            binding.tvName.setText(producto.getNombre());
            binding.ivImage.setImageResource(R.mipmap.ic_out_producto);
            binding.tvPrice.setText(producto.getPrecio()+"");
            binding.tvBrand.setText(producto.getDescripcion());
        }
    }

    public interface ProductClickListener {
        void onProductClick(Producto product);
    }

    private static class ProductoDiffCallback extends DiffUtil.ItemCallback<Producto> {
        @Override
        public boolean areItemsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
            return oldItem.equals(newItem);
        }
    }
}
