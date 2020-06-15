package com.tolaotesanya.kindeed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tolaotesanya.kindeed.activities.NewProductActivity;
import com.tolaotesanya.kindeed.activities.auth.AuthActivity;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.dependencies.DependencyRegistry;
import com.tolaotesanya.kindeed.helper.BottomNavPresenter;
import com.tolaotesanya.kindeed.helper.CustomAdapter;
import com.tolaotesanya.kindeed.modellayer.database.AppDatabase;
import com.tolaotesanya.kindeed.modellayer.model.Constants;
import com.tolaotesanya.kindeed.modellayer.model.Product;
import com.tolaotesanya.kindeed.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int ACTIVITY_NUM = 0 ;
    public static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;
    RecyclerView recyclerView, recyclerView2;
    //ArrayList<String> source, source2, source3;
    private IntentPresenter intentPresenter;
    private ProductViewModel productViewModel;
    private CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBottomNav();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView2 =  findViewById(R.id.recyclerview2);
        //AddItemsToRecyclerViewArrayList();


        addRec1();
        addRec2();

        DependencyRegistry.shared.inject(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                startActivityForResult(intent, NEW_PRODUCT_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void configureWith(ProductViewModel productViewModel, IntentPresenter intentPresenter) {
        this.productViewModel = productViewModel;
        this.intentPresenter = intentPresenter;

        productViewModel.getmAllproducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setmProduct(products);
            }
        });


    }

    public void addRec1(){
        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        int layoutid = R.layout.recycler_service;
         adapter = new CustomAdapter(layoutid, intentPresenter, this);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);

    }

    public void addRec2(){

        RecyclerView.LayoutManager recyclerViewLayoutManager  = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(recyclerViewLayoutManager);
        int layoutid = R.layout.recycler_items;
        adapter = new CustomAdapter(layoutid, intentPresenter, this);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(HorizontalLayout);
        recyclerView2.setAdapter(adapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra(Constants.PRODUCT_NAME);
            String desc = data.getStringExtra(Constants.PRODUCT_DESC);
            String category = data.getStringExtra(Constants.PRODUCT_CATEGORY);
            String price = data.getStringExtra(Constants.PRODUCT_PRICE);
            double newPrice = Double.parseDouble(price);
            String image = data.getStringExtra(Constants.PRODUCT_IMAGE);

            Product product = new Product(null, name, desc, category, newPrice, image);
            productViewModel.insert(product);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Product not saved",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void setupBottomNav() {
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavPresenter bottomNavPresenter = new BottomNavPresenter(MainActivity.this, ACTIVITY_NUM);
        bottomNavPresenter.setupBottomNavigationView(bottomNavigationViewEx);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.main_logout_btn){
            logout();
        }
        return true;
    }

    private void logout() {
        Intent intent = new Intent(this, AuthActivity.class);
        intent.putExtra(AuthActivity.EXTRA_CLEAR_CREDENTIALS, true);
        startActivity(intent);
        finish();
    }

   /* // Function to add items in RecyclerView.
    public void AddItemsToRecyclerViewArrayList() {
        // Adding items to ArrayList
        source = new ArrayList<>();
        source.add("Non-Organic");
        source.add("Organic");
        source.add("All");
        source.add("Food Donation");

        source2 = new ArrayList<>();
        source2.add("Banana");
        source2.add("Chocolate");
        source2.add("Milk");

        source3 = new ArrayList<>();
        source3.add("Donate Sandwish");
        source3.add("Donate Drinks");
        source3.add("Donate Sweets");

    }*/
}
