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
    private LiveData<List<Product>> mAllproductsOrganic;
    private LiveData<List<Product>> mAllproductsNonOrganic;
    private LiveData<List<Product>> mAllproductsDonate;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllproducts = mRepository.getmProducts();
        mAllproductsOrganic = mRepository.getmProductsOrganic();
        mAllproductsNonOrganic = mRepository.getmProductsNonOrganic();
        mAllproductsDonate = mRepository.getmProductsDonate();
    }

    public LiveData<List<Product>> getmAllproducts() {
        return mAllproducts;
    }

    public LiveData<List<Product>> getmAllproductsOrganic() {
        return mAllproductsOrganic;
    }

    public LiveData<List<Product>> getmAllproductsNonOrganic() {
        return mAllproductsNonOrganic;
    }

    public LiveData<List<Product>> getmAllproductsDonate() {
        return mAllproductsDonate;
    }

    public void insert(Product product){
        mRepository.insert(product);
    }
}
