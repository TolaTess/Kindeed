package com.tolaotesanya.kindeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tolaotesanya.kindeed.activities.auth.AuthActivity;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.dependencies.DependencyRegistry;
import com.tolaotesanya.kindeed.helper.BottomNavPresenter;
import com.tolaotesanya.kindeed.helper.CustomAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 0 ;
    RecyclerView recyclerView, recyclerView2;
    ArrayList<String> source, source2, source3;
    private IntentPresenter intentPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setupToolbar();
        setupBottomNav();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView2 =  findViewById(R.id.recyclerview2);
        AddItemsToRecyclerViewArrayList();
        DependencyRegistry.shared.inject(this);

        attachRecyclerUI();

    }

    private void attachRecyclerUI() {
        CardView popular = findViewById(R.id.popular);
        CardView food = findViewById(R.id.food);
        CardView help = findViewById(R.id.help);

        TextView popularText = popular.findViewById(R.id.title_subheading);
        TextView foodText = food.findViewById(R.id.title_subheading);
        TextView helpText = help.findViewById(R.id.title_subheading);

        popularText.setText("Popular");
        foodText.setText("Food");
        helpText.setText("Help");

        checkSource("popular");

        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSource("popular");
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSource("food");
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSource("help");
            }
        });
    }

    public void configureWith(IntentPresenter intentPresenter) {
        this.intentPresenter = intentPresenter;
    }

    public void addRec1(ArrayList<String> source){
        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        int layoutid = R.layout.recycler_items;
        CustomAdapter adapter = new CustomAdapter(source, layoutid, intentPresenter, this);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);
    }

    public void addRec2(ArrayList<String> source2){
        RecyclerView.LayoutManager recyclerViewLayoutManager  = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(recyclerViewLayoutManager);
        int layoutid = R.layout.recycler_service;
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
        source.add("Banana");
        source.add("Bread");
        source.add("Milk");

        source2 = new ArrayList<>();
        source2.add("Fish");
        source2.add("Chocolate");
        source2.add("Rice");

        source3 = new ArrayList<>();
        source3.add("Food Drive");
        source3.add("Commune Cleaning");
        source3.add("Help");

    }

    public void checkSource(String textView){
        switch (textView){
            case "popular":
                addRec1(source);
                addRec2(source);
                break;
            case "food":
                addRec1(source2);
                addRec2(source2);
                break;
            case "help":
                addRec1(source3);
                addRec2(source3);
                break;
        }
    }


}
