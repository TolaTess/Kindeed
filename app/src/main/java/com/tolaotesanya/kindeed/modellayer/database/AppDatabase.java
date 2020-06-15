package com.tolaotesanya.kindeed.modellayer.database;

import android.content.Context;

import com.tolaotesanya.kindeed.modellayer.dao.ProductDAO;
import com.tolaotesanya.kindeed.modellayer.dao.UserDAO;
import com.tolaotesanya.kindeed.modellayer.model.Product;
import com.tolaotesanya.kindeed.modellayer.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Product.class, User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;

    public abstract ProductDAO productDao();
    public abstract UserDAO userDAO();

    private static volatile AppDatabase instance;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context){
        if(instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "kindeed_database")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ProductDAO pDAO = instance.productDao();
                // If you want to keep data through app restarts,
                // comment out the following
                //dao.deleteAll();

                Product product = new Product(null, "Bread", "nice yummy bread", "Non-Organic", 1.20, "bread.jbg");
                pDAO.insertAll(product);
            });
        }
    };

}
