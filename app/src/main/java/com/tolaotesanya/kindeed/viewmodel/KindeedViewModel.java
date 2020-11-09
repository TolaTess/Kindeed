package com.tolaotesanya.kindeed.viewmodel;

import com.tolaotesanya.kindeed.modellayer.model.Product;
import com.tolaotesanya.kindeed.repositories.CartRepository;
import com.tolaotesanya.kindeed.repositories.KindeedRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KindeedViewModel extends ViewModel {

    KindeedRepository repository = new KindeedRepository();
    CartRepository cartRepository = new CartRepository();
    MutableLiveData<Product> mutableProduct = new MutableLiveData<>();

    public LiveData<List<Product>> getProducts(){
        return repository.getProducts();
    }

    public void setProduct(Product product) {
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct() {
        return mutableProduct;
    }

    public boolean addItemToCart(Product product) {
        return cartRepository.addItemToCart(product);
    }

}
