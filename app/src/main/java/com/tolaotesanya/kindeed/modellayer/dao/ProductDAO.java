package com.tolaotesanya.kindeed.modellayer.dao;

import com.tolaotesanya.kindeed.modellayer.model.Product;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ProductDAO {

    @Insert
    void insertAll(List<Product> items);

    @Insert
    void insertAll(Product... items);

    @Query("DELETE FROM Product")
    void deleteAll();

    @Query("SELECT * from Product ORDER BY category ASC")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * from Product WHERE category ='Organic'")
    LiveData<List<Product>> getmProductsbyOrganic();

    @Query("SELECT * from Product WHERE category ='Non-Organic'")
    LiveData<List<Product>> getmProductsbyNonOrganic();

    @Query("SELECT * from Product WHERE category ='Donate'")
    LiveData<List<Product>> getmProductsbyDonate();

    @Query("SELECT COUNT(*) from Product")
    int countItems();
}
