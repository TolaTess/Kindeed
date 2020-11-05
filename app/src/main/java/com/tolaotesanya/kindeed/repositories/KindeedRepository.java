package com.tolaotesanya.kindeed.repositories;

import com.tolaotesanya.kindeed.modellayer.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class KindeedRepository {

    private MutableLiveData<List<Product>> mutableLiveData;

    public LiveData<List<Product>> getProducts(){
        if(mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
            loadProducts();
        }
        return mutableLiveData;
    }

    private void loadProducts() {
        //get list of products
        List<Product> productList = new ArrayList<>();
        //room or database call here
        productList.add(new Product(UUID.randomUUID().toString(), "Sandwish",
                "donate 12 sandwish to homeless people in Dublin city center",
                "food",24, "null"));
        productList.add(new Product(UUID.randomUUID().toString(), "Chocolate",
                "send 10 box of chocolate to children in Drogheda hospital",
                "treats",40, "null"));
        productList.add(new Product(UUID.randomUUID().toString(), "Haircut",
                "give 4 homeless people haircut",
                "grooming",40, "null"));
        mutableLiveData.setValue(productList);
    }
}
