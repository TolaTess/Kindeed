package com.tolaotesanya.kindeed.activities;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.dependencies.DependencyRegistry;
import com.tolaotesanya.kindeed.helper.BottomNavPresenter;
import com.tolaotesanya.kindeed.helper.CustomAdapter;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class KindeedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> source;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    CustomAdapter adapter;
    LinearLayoutManager HorizontalLayout;
    private IntentPresenter intentPresenter;

    private static final int ACTIVITY_NUM = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kindeed);
        setupToolbar();
        setupBottomNav();

        recyclerView = findViewById(R.id.recyclerview);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        // Adding items to RecyclerView.
        AddItemsToRecyclerViewArrayList();
        DependencyRegistry.shared.inject(this);
        // with source list as a parameter
        int layoutid = R.layout.recycler_basket;
        adapter = new CustomAdapter(layoutid, intentPresenter, this, source);
        // Set Horizontal Layout Manager
        // for Recycler view
        HorizontalLayout = new LinearLayoutManager(KindeedActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        // Set adapter on recycler view
        recyclerView.setAdapter(adapter);

    }

    public void configureWith(IntentPresenter intentPresenter) {
        this.intentPresenter = intentPresenter;
    }

    private void setupToolbar() {
        RelativeLayout mToolbar = findViewById(R.id.kindeed_toolbar);
        TextView toolbarText = mToolbar.findViewById(R.id.title_toolbar);
        TextView toolbarDesc = mToolbar.findViewById(R.id.desc_toolbar);
        toolbarText.setText("Tola");
        toolbarDesc.setText("your basket");
    }

    private void setupBottomNav() {
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavPresenter bottomNavPresenter = new BottomNavPresenter(KindeedActivity.this, ACTIVITY_NUM);
        bottomNavPresenter.setupBottomNavigationView(bottomNavigationViewEx);
    }


    // Function to add items in RecyclerView.
    public void AddItemsToRecyclerViewArrayList() {
        // Adding items to ArrayList
        source = new ArrayList<>();
        source.add("fish");
        source.add("avocado");
        source.add("banana");
    }


}

