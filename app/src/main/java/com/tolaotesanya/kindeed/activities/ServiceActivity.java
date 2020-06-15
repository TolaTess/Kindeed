package com.tolaotesanya.kindeed.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.tolaotesanya.kindeed.helper.CustomAdapter;
import com.tolaotesanya.kindeed.modellayer.database.AppDatabase;
import com.tolaotesanya.kindeed.viewmodel.ProductViewModel;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    RecyclerView recyclerView, recyclerView2;
    ArrayList<String> source, source2, source3, source4;
    private IntentPresenter intentPresenter;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

         String itemName = getIntent().getStringExtra("serviceName");
         setupToolbar(itemName);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView2 =  findViewById(R.id.recyclerview2);
        //AddItemsToRecyclerViewArrayList();
        DependencyRegistry.shared.inject(this);
        attachRecyclerUI();


    }

    public void configureWith(ProductViewModel productViewModel, IntentPresenter intentPresenter) {
        this.productViewModel = productViewModel;
        this.intentPresenter = intentPresenter;
    }


    public void addRec1(ArrayList<String> source){
        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        int layoutid = R.layout.recycler_items;
        CustomAdapter adapter = new CustomAdapter(layoutid, intentPresenter, this, source);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(ServiceActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);
    }

    public void addRec2(ArrayList<String> source2){
        RecyclerView.LayoutManager recyclerViewLayoutManager  = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(recyclerViewLayoutManager);
        int layoutid = R.layout.recycler_items;
        CustomAdapter adapter = new CustomAdapter(layoutid, intentPresenter, this, source2);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(ServiceActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(HorizontalLayout);
        recyclerView2.setAdapter(adapter);
    }

   /* // Function to add items in RecyclerView.
    public void AddItemsToRecyclerViewArrayList() {
        // Adding items to ArrayList
        source = new ArrayList<>();
        source.add("Veggies");
        source.add("Protein");
        source.add("Drinks");

        source2 = new ArrayList<>();
        source2.add("Cabbage");
        source2.add("Kale");
        source2.add("Spinach");

        source3 = new ArrayList<>();
        source3.add("Beef");
        source3.add("Chicken");
        source3.add("Beans");

        source4 = new ArrayList<>();
        source4.add("Coke");
        source4.add("Water");
        source4.add("Beer");

    }*/

    private void setupToolbar(String name) {
        RelativeLayout mToolbar = findViewById(R.id.service_app_toolbar);
        TextView toolbarText = mToolbar.findViewById(R.id.item_title_toolbar);
        ImageView toolbarArrow = mToolbar.findViewById(R.id.item_image_toolbar);

        toolbarText.setText(name);
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
                addRec1(source2);
                addRec2(source3);
                break;
            case "protein":
                serviceText.setText("Perfect with Protein");
                addRec1(source3);
                addRec2(source);
                break;
            case "popular":
                serviceText.setText("Perfect with");
                addRec1(source4);
                addRec2(source2);
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
