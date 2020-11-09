package com.tolaotesanya.kindeed.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

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
        }
    }

    public interface CartInterface{
        void deleteItem(CartItem cartItem);
        void changeQuantity(CartItem cartItem, int quantity);
    }
}
