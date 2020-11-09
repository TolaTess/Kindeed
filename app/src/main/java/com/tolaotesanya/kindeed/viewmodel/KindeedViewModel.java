package com.tolaotesanya.kindeed.viewmodel;

import com.tolaotesanya.kindeed.modellayer.model.CartItem;
import com.tolaotesanya.kindeed.modellayer.model.Product;
import com.tolaotesanya.kindeed.repositories.CartRepository;
import com.tolaotesanya.kindeed.repositories.KindeedRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KindeedViewModel extends ViewModel {

    private KindeedRepository repository = new KindeedRepository();
    private CartRepository cartRepository = new CartRepository();
    private MutableLiveData<Product> mutableProduct = new MutableLiveData<>();

    public LiveData<List<Product>> getProducts(){
        return repository.getProducts();
    }

    public LiveData<Product> getProduct(){
        return mutableProduct;
    }

    public void setProduct(Product product) {
        mutableProduct.setValue(product);
    }

    public LiveData<List<CartItem>> getCart() {
        return cartRepository.getCart();
    }

    public boolean addItemToCart(Product product) {
        return cartRepository.addItemToCart(product);
    }

    public void removeItemFromCart(CartItem cartItem){
        cartRepository.removeItemFromCart(cartItem);
    }

    public void changeQuantity(CartItem cartItem, int quantity){
        cartRepository.changeQuantity(cartItem, quantity);
    }

}
