package com.tolaotesanya.kindeed.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.tolaotesanya.kindeed.databinding.RecyclerServiceBinding;
import com.tolaotesanya.kindeed.modellayer.model.Product;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ShopAdapter extends ListAdapter<Product, ShopAdapter.ViewHolder> {


    protected ShopAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerServiceBinding recyclerItemsBinding = RecyclerServiceBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(recyclerItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ViewHolder holder, int position) {
        //Product product = getItem(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerServiceBinding recyclerItemsBinding;

        public ViewHolder(@NonNull RecyclerServiceBinding binding) {
            super(binding.getRoot());
            this.recyclerItemsBinding = binding;
        }
    }

    public interface MainInterface{
        void addItem(Product product);
        void onItemClick (Product product);
    }
}
