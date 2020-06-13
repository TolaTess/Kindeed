package com.tolaotesanya.kindeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tolaotesanya.kindeed.activities.AccountActivity;
import com.tolaotesanya.kindeed.activities.auth.AuthActivity;
import com.tolaotesanya.kindeed.helper.BottomNavPresenter;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();


        //Obtain the token from the Intent's extras
        String accessToken = getIntent().getStringExtra(AuthActivity.EXTRA_ACCESS_TOKEN);
        TextView textView = findViewById(R.id.credentials);
        textView.setText(accessToken);
        setupBottomNav();
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
        Toolbar mToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Kindeed");
    }


    private void logout() {
        Intent intent = new Intent(this, AuthActivity.class);
        intent.putExtra(AuthActivity.EXTRA_CLEAR_CREDENTIALS, true);
        startActivity(intent);
        finish();
    }
}
