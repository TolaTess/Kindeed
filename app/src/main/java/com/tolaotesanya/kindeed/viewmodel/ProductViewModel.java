package com.tolaotesanya.kindeed.viewmodel;

import android.app.Application;

import com.tolaotesanya.kindeed.modellayer.model.Product;
import com.tolaotesanya.kindeed.modellayer.repository.ProductRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository mRepository;
    private LiveData<List<Product>> mAllproducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllproducts = mRepository.getmProducts();
    }

    public LiveData<List<Product>> getmAllproducts() {
        return mAllproducts;
    }

    public void insert(Product product){
        mRepository.insert(product);
    }
}
