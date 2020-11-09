package com.tolaotesanya.kindeed.repositories;

import com.tolaotesanya.kindeed.modellayer.model.CartItem;
import com.tolaotesanya.kindeed.modellayer.model.Product;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CartRepository {

    private MutableLiveData<List<CartItem>> mutableLiveCart = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if(mutableLiveCart.getValue() == null){
            initCart();
        } return mutableLiveCart;
    }

    public void initCart() {
        mutableLiveCart.setValue(new ArrayList<CartItem>());
    }

    ///check if item has been added to cart max of 5
    public boolean addItemToCart(Product product) {
        if(mutableLiveCart.getValue() == null){
            initCart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableLiveCart.getValue());
        //check if item already in the cart - merge
        for(CartItem cartItem: cartItemList) {
            if (cartItem.getProduct().getItemId().equals(product.getItemId())) {
                // max 5
                if (cartItem.getQuantity() == 5) {
                    return false;
                }
                //update cart item
                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemList.set(index, cartItem);
                mutableLiveCart.setValue(cartItemList);

                return true;
            }
        }
        CartItem cartItem = new CartItem(product, 1);
        cartItemList.add(cartItem);
        mutableLiveCart.setValue(cartItemList);
        return true;
    }
}
