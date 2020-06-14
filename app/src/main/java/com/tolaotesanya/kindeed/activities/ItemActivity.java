package com.tolaotesanya.kindeed.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tolaotesanya.kindeed.R;

public class ItemActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        String itemname = getIntent().getStringExtra("itemName");
        setupToolbar(itemname);

    }

    private void setupToolbar(String name) {
        RelativeLayout mToolbar = findViewById(R.id.item_toolbar);
        TextView toolbarText = mToolbar.findViewById(R.id.item_title_toolbar);
        toolbarText.setText(name);
    }
}
