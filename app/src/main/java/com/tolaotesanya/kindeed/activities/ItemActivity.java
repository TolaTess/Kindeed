package com.tolaotesanya.kindeed.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tolaotesanya.kindeed.MainActivity;
import com.tolaotesanya.kindeed.R;

import java.text.NumberFormat;

public class ItemActivity extends AppCompatActivity {
    private static final String TAG = "ItemActivity";

    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        String itemname = getIntent().getStringExtra("itemName");
        String desc = getIntent().getStringExtra("desc");
        String price = getIntent().getStringExtra("price");
        setupUI(itemname, desc, price);

    }

    private void setupUI(String name, String desc, String price) {
        RelativeLayout mToolbar = findViewById(R.id.item_toolbar);
        TextView toolbarText = mToolbar.findViewById(R.id.item_title_toolbar);
        TextView toolbarDesc = mToolbar.findViewById(R.id.item_desc_toolbar);
        ImageView toolbarArrow = mToolbar.findViewById(R.id.item_image_toolbar);
        TextView priceText = findViewById(R.id.item_price);
        CardView cardView = findViewById(R.id.quanity);
        ImageView cardViewMinus = cardView.findViewById(R.id.quanity_minus);
        ImageView cardViewPlus = cardView.findViewById(R.id.quanity_plus);
        TextView quanityText = cardView.findViewById(R.id.quantity_count);

        toolbarText.setText(name);
        toolbarDesc.setText(desc);
        priceText.setText(price + " EUR");
        toolbarArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);

        cardViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity != 0){
                    quantity = --quantity;
                    String newQuantity = String.valueOf(quantity);
                    double doublePrice = Double.parseDouble(price);
                    double updatePrice = doublePrice * quantity;
                    String newPrice = String.valueOf(nf.format(updatePrice));
                    quanityText.setText(newQuantity);
                    priceText.setText(newPrice + " EUR");
                }
            }
        });

        cardViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = ++quantity;
                String newQuantity = String.valueOf(quantity);
                double doublePrice = Double.parseDouble(price);
                double updatePrice = doublePrice * quantity;
                String newPrice = String.valueOf(nf.format(updatePrice));
                quanityText.setText(newQuantity);
                priceText.setText(newPrice + " EUR");
            }
        });

    }
}
