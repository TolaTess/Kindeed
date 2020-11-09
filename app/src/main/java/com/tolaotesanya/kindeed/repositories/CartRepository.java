package com.tolaotesanya.kindeed.repositories;

import com.tolaotesanya.kindeed.modellayer.model.CartItem;
import com.tolaotesanya.kindeed.modellayer.model.Product;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CartRepository {

    private MutableLiveData<List<CartItem>> mutableLiveCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableLiveTotalPrice = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if(mutableLiveCart.getValue() == null){
            initCart();
        } return mutableLiveCart;
    }

    public void initCart() {
        mutableLiveCart.setValue(new ArrayList<CartItem>());
        calculateCartTotal();
    }

    //Check if item has been added to cart max of 5
    public boolean addItemToCart(Product product) {
        if(mutableLiveCart.getValue() == null){
            initCart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableLiveCart.getValue());
        //Check if item already in the cart - merge
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
                calculateCartTotal();
                return true;
            }
        }
        CartItem cartItem = new CartItem(product, 1);
        cartItemList.add(cartItem);
        mutableLiveCart.setValue(cartItemList);
        calculateCartTotal();
        return true;
    }

    public void removeItemFromCart(CartItem cartItem) {
        if(mutableLiveCart.getValue() == null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableLiveCart.getValue());
        cartItemList.remove(cartItem);
        mutableLiveCart.setValue(cartItemList);
        //Recalculate total price after removing item
        calculateCartTotal();
    }

    public void changeQuantity(CartItem cartItem, int quantity) {
        if(mutableLiveCart.getValue() == null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableLiveCart.getValue());
        CartItem updatedItem = new CartItem(cartItem.getProduct(), quantity);
        cartItemList.set(cartItemList.indexOf(cartItem), updatedItem);
        mutableLiveCart.setValue(cartItemList);
        //Call total price of cart
        calculateCartTotal();
    }

    public LiveData<Double> getTotalPrice() {
        if(mutableLiveTotalPrice.getValue() == null){
            mutableLiveTotalPrice.setValue(0.0);
        }
        return mutableLiveTotalPrice;
    }

    private void calculateCartTotal(){
        if(mutableLiveCart.getValue() == null) return;
        double total = 0.0;
        List<CartItem> cartItemList = mutableLiveCart.getValue();
        for(CartItem cartItem: cartItemList){
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        mutableLiveTotalPrice.setValue(total);
    }
}
