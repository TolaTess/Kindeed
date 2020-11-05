package com.tolaotesanya.kindeed.viewmodel;

import com.tolaotesanya.kindeed.modellayer.model.Product;
import com.tolaotesanya.kindeed.repositories.KindeedRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class KindeedViewModel extends ViewModel {

    KindeedRepository repository = new KindeedRepository();

    public LiveData<List<Product>> getProducts(){
        return repository.getProducts();
    }

}
