package com.tolaotesanya.kindeed.modellayer.dao;

import com.tolaotesanya.kindeed.modellayer.model.Product;
import com.tolaotesanya.kindeed.modellayer.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<User> users);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(User... users);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("SELECT * from User ORDER BY name ASC")
    LiveData<List<User>> getProductsbyCategory();

    @Query("SELECT COUNT(*) from User")
    int countItems();

}
