package com.tolaotesanya.kindeed.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.tolaotesanya.kindeed.R;

public class ItemActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        TextView name = findViewById(R.id.item_text);

        String itemname = getIntent().getStringExtra("itemName");
        name.setText(itemname);

    }
}
