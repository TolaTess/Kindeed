package com.tolaotesanya.kindeed.modellayer.repository;

import android.app.Application;

import com.tolaotesanya.kindeed.modellayer.dao.ProductDAO;
import com.tolaotesanya.kindeed.modellayer.database.AppDatabase;
import com.tolaotesanya.kindeed.modellayer.model.Product;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ProductRepository {

    private ProductDAO mProductDao;
    private LiveData<List<Product>> mProducts;
    private LiveData<List<Product>> mProductsOrganic;
    private LiveData<List<Product>> mProductsNonOrganic;
    private LiveData<List<Product>> mProductsDonate;

    public ProductRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mProductDao = database.productDao();
        mProducts = mProductDao.getAllProducts();
        mProductsOrganic = mProductDao.getmProductsbyOrganic();
        mProductsNonOrganic = mProductDao.getmProductsbyNonOrganic();
        mProductsDonate = mProductDao.getmProductsbyDonate();
    }

    public LiveData<List<Product>> getmProducts() {
        return mProducts;
    }

    public LiveData<List<Product>> getmProductsOrganic() {
        return mProductsOrganic;
    }

    public LiveData<List<Product>> getmProductsNonOrganic() {
        return mProductsNonOrganic;
    }

    public LiveData<List<Product>> getmProductsDonate() {
        return mProductsDonate;
    }

    public void insert(Product product){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.insertAll(product);
        });
    }

}
