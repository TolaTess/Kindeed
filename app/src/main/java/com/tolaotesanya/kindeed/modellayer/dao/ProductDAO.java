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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Product> items);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Product... items);

    @Query("DELETE FROM Product")
    void deleteAll();

    @Query("SELECT * from Product ORDER BY category ASC")
    LiveData<List<Product>> getProductsbyCategory();

    @Query("SELECT COUNT(*) from Product")
    int countItems();
}
