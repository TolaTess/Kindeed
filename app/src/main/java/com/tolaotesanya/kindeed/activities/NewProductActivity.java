package com.tolaotesanya.kindeed.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.modellayer.model.Constants;

public class NewProductActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mDesc;
    private EditText mCategory;
    private EditText mPrice;
    private EditText mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        mName = findViewById(R.id.edit_item_name);
        mDesc = findViewById(R.id.edit_item_desc);
        mCategory = findViewById(R.id.edit_item_cat);
        mPrice = findViewById(R.id.edit_item_price);
        mImage = findViewById(R.id.edit_item_image);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name = mName.getText().toString();
                    replyIntent.putExtra(Constants.PRODUCT_NAME, name);
                    String desc = mDesc.getText().toString();
                    replyIntent.putExtra(Constants.PRODUCT_DESC, desc);
                    String category = mCategory.getText().toString();
                    replyIntent.putExtra(Constants.PRODUCT_CATEGORY, category);
                    String price = mPrice.getText().toString();
                    replyIntent.putExtra(Constants.PRODUCT_PRICE, price);
                    String image = mImage.getText().toString();
                    replyIntent.putExtra(Constants.PRODUCT_IMAGE, image);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
