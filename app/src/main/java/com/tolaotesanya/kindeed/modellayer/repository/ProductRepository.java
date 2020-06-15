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

    public ProductRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mProductDao = database.productDao();
        mProducts = mProductDao.getProductsbyCategory();
    }

    public LiveData<List<Product>> getmProducts() {
        return mProducts;
    }

    public void insert(Product product){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.insertAll(product);
        });
    }

}
