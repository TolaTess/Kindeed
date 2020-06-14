package com.tolaotesanya.kindeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tolaotesanya.kindeed.activities.auth.AuthActivity;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.dependencies.DependencyRegistry;
import com.tolaotesanya.kindeed.helper.BottomNavPresenter;
import com.tolaotesanya.kindeed.helper.CustomAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 0 ;
    RecyclerView recyclerView, recyclerView2;
    ArrayList<String> source;
    ArrayList<String> source2;
    private IntentPresenter intentPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();

        //Obtain the token from the Intent's extras
        String accessToken = getIntent().getStringExtra(AuthActivity.EXTRA_ACCESS_TOKEN);
        TextView textView = findViewById(R.id.credentials);
        textView.setText(accessToken);
        setupBottomNav();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView2 =  findViewById(R.id.recyclerview2);
        AddItemsToRecyclerViewArrayList();
        DependencyRegistry.shared.inject(this);
        addRec1();
        addRec2();



    }
    public void configureWith(IntentPresenter intentPresenter) {
        this.intentPresenter = intentPresenter;
    }

    public void addRec1(){
        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        int layoutid = R.layout.recycler_service;
        CustomAdapter adapter = new CustomAdapter(source, layoutid, intentPresenter, this);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);
    }

    public void addRec2(){
        RecyclerView.LayoutManager recyclerViewLayoutManager  = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(recyclerViewLayoutManager);
        int layoutid = R.layout.recycler_items;
        CustomAdapter adapter = new CustomAdapter(source2, layoutid, intentPresenter, this);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(HorizontalLayout);
        recyclerView2.setAdapter(adapter);
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

    private void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Kindeed");
    }


    private void logout() {
        Intent intent = new Intent(this, AuthActivity.class);
        intent.putExtra(AuthActivity.EXTRA_CLEAR_CREDENTIALS, true);
        startActivity(intent);
        finish();
    }

    // Function to add items in RecyclerView.
    public void AddItemsToRecyclerViewArrayList() {
        // Adding items to ArrayList
        source = new ArrayList<>();
        source.add("Foods");
        source.add("HandyWork");
        source.add("TimeShare");

        source2 = new ArrayList<>();
        source2.add("fish");
        source2.add("avocado");
        source2.add("banana");
        source2.add("rice");
        source2.add("potato");
        source2.add("tomatoes");
        source2.add("cabbage");
    }


}
