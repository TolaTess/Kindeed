package com.tolaotesanya.kindeed.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.tolaotesanya.kindeed.databinding.RecyclerBasketBinding;
import com.tolaotesanya.kindeed.modellayer.model.CartItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends ListAdapter<CartItem, CartAdapter.CartViewHolder> {

    private CartInterface cartInterface;

    public CartAdapter(CartInterface cartInterface) {
        super(CartItem.itemCallback);
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerBasketBinding recyclerBasketBinding = RecyclerBasketBinding.inflate(layoutInflater, parent, false);
        return new CartViewHolder(recyclerBasketBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.recyclerBasketBinding.setCartItem(getItem(position));
        holder.recyclerBasketBinding.executePendingBindings();
    }


    class CartViewHolder extends RecyclerView.ViewHolder{

        RecyclerBasketBinding recyclerBasketBinding;

        public CartViewHolder(@NonNull RecyclerBasketBinding binding) {
            super(binding.getRoot());
            this.recyclerBasketBinding = binding;

            recyclerBasketBinding.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartInterface.deleteItem(getItem(getBindingAdapterPosition()));
                }
            });

            recyclerBasketBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int quantity = position + 1;
                    if(quantity == getItem(getBindingAdapterPosition()).getQuantity()){
                        return;
                    }
                    cartInterface.changeQuantity(getItem(getBindingAdapterPosition()), quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public interface CartInterface{
        void deleteItem(CartItem cartItem);
        void changeQuantity(CartItem cartItem, int quantity);
    }
}
