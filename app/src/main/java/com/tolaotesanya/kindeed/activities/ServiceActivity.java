package com.tolaotesanya.kindeed.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tolaotesanya.kindeed.MainActivity;
import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.dependencies.DependencyRegistry;
import com.tolaotesanya.kindeed.helper.CategoryCustomAdapter;
import com.tolaotesanya.kindeed.modellayer.model.Product;
import com.tolaotesanya.kindeed.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceActivity extends AppCompatActivity {

    RecyclerView recyclerView, recyclerView2;
    ArrayList<String> source, source2, source3, source4;
    private IntentPresenter intentPresenter;
    private ProductViewModel productViewModel;
    private CategoryCustomAdapter adapter;
    private CategoryCustomAdapter perfectWithAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

         String category = getIntent().getStringExtra("category");
         setupToolbar(category);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView2 =  findViewById(R.id.recyclerview2);

        attachRecyclerUI();
        addRec1(category);
        addRec2(category);

        DependencyRegistry.shared.inject(this);

    }

    public void configureWith(ProductViewModel productViewModel, IntentPresenter intentPresenter) {
        this.productViewModel = productViewModel;
        this.intentPresenter = intentPresenter;

        productViewModel.getmAllproducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setmProduct(products);
                perfectWithAdapter.setmProduct(products);
            }
        });

    }


    public void addRec1(String category){
        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new CategoryCustomAdapter(category, intentPresenter, this);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(ServiceActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);
    }

    public void addRec2(String category){
        RecyclerView.LayoutManager recyclerViewLayoutManager  = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(recyclerViewLayoutManager);
        perfectWithAdapter = new CategoryCustomAdapter(category, intentPresenter, this);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(ServiceActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(HorizontalLayout);
        recyclerView2.setAdapter(perfectWithAdapter);
    }

    private void setupToolbar(String category) {
        RelativeLayout mToolbar = findViewById(R.id.service_app_toolbar);
        TextView toolbarText = mToolbar.findViewById(R.id.item_title_toolbar);
        ImageView toolbarArrow = mToolbar.findViewById(R.id.item_image_toolbar);

        toolbarText.setText(category);
        toolbarArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }


    public void checkSource(String textView){
        TextView serviceText = findViewById(R.id.service_item_text);
        switch (textView){
            case "veggies":
                serviceText.setText("Perfect with Veggies");
                break;
            case "protein":
                serviceText.setText("Perfect with Protein");
                break;
            case "popular":
                serviceText.setText("Perfect with");
                break;
        }
    }

        private void attachRecyclerUI() {
        CardView popular = findViewById(R.id.popular);
        CardView veggies = findViewById(R.id.veggies);
        CardView protein = findViewById(R.id.protein);

        TextView popularText = popular.findViewById(R.id.title_subheading);
        TextView veggiesText = veggies.findViewById(R.id.title_subheading);
        TextView proteinText = protein.findViewById(R.id.title_subheading);

        popularText.setText("Popular");
        veggiesText.setText("Vegetables");
        proteinText.setText("Protein");

        checkSource("popular");

        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSource("popular");
            }
        });

        veggies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSource("veggies");
            }
        });

        protein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSource("protein");
            }
        });
    }



}
