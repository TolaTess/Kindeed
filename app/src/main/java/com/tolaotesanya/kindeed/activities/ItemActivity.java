package com.tolaotesanya.kindeed.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tolaotesanya.kindeed.MainActivity;
import com.tolaotesanya.kindeed.R;

public class ItemActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        String itemname = getIntent().getStringExtra("itemName");
        String desc = getIntent().getStringExtra("desc");
        String price = getIntent().getStringExtra("price");
        setupToolbar(itemname, desc, price);

    }

    private void setupToolbar(String name, String desc, String price) {
        RelativeLayout mToolbar = findViewById(R.id.item_toolbar);
        TextView toolbarText = mToolbar.findViewById(R.id.item_title_toolbar);
        TextView toolbarDesc = mToolbar.findViewById(R.id.item_desc_toolbar);
        ImageView toolbarArrow = mToolbar.findViewById(R.id.item_image_toolbar);
        TextView priceText = findViewById(R.id.item_price);

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
    }
}
