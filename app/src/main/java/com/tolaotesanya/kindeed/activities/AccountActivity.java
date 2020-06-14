package com.tolaotesanya.kindeed.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.helper.BottomNavPresenter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AccountActivity extends AppCompatActivity {


    private static final int ACTIVITY_NUM = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //setupToolbar();
        setupBottomNav();

    }
   /* private void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.account_toolbar);
        TextView toolbarText = mToolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        toolbarText.setText("Account");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }*/

    private void setupBottomNav() {
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavPresenter bottomNavPresenter = new BottomNavPresenter(AccountActivity.this, ACTIVITY_NUM);
        bottomNavPresenter.setupBottomNavigationView(bottomNavigationViewEx);
    }
}
