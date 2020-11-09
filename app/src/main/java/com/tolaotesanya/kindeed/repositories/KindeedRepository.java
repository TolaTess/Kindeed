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
                "food",24, 3, "https://firebasestorage.googleapis.com/v0/b/appchatapplication-8a71b.appspot.com/o/profile_images%2F8aSrAs6HsTWv7CyQVr0Qyxx7tGe2.jpg?alt=media&token=a8cc46dd-1ce0-48a4-ab91-7efc86e24d3a"));
        productList.add(new Product(UUID.randomUUID().toString(), "Chocolate",
                "send 10 box of chocolate to children in Drogheda hospital",
                "treats",40, 3, "https://firebasestorage.googleapis.com/v0/b/appchatapplication-8a71b.appspot.com/o/profile_images%2FeGTOsqTba8SPmJ4Cej7DqldoEtW2.jpg?alt=media&token=5e43cde9-1f36-4b5f-90fb-fa8df2feffbf"));
        productList.add(new Product(UUID.randomUUID().toString(), "Haircut",
                "give 4 homeless people haircut",
                "grooming",40, 3, "https://firebasestorage.googleapis.com/v0/b/appchatapplication-8a71b.appspot.com/o/profile_images%2FuJQiJMf3MJa7cnlVIg5B294Ykk22.jpg?alt=media&token=24acdf84-32f9-4059-be10-0a415dedfa83"));
        productList.add(new Product(UUID.randomUUID().toString(), "Tops",
                "give 5 tops for homeless people",
                "grooming",30, 3, "https://firebasestorage.googleapis.com/v0/b/appchatapplication-8a71b.appspot.com/o/profile_images%2FyH8TZlXmfNRWz7We7SXBMgVCNYc2.jpg?alt=media&token=aad7588a-4995-4d8d-a242-eb4974e02a01"));
        mutableLiveData.setValue(productList);
    }
}
