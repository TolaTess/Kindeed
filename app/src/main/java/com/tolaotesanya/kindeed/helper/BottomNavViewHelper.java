package com.tolaotesanya.kindeed.helper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.views.activities.AccountActivity;
import com.tolaotesanya.kindeed.views.activities.MainActivity;

import androidx.annotation.NonNull;

public class BottomNavViewHelper {

    private static final String TAG = "BottomNavViewHelper";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        //bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(context, MainActivity.class); //ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        break;
                    case R.id.profile:
                        Intent intent2 = new Intent(context, AccountActivity.class); //ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}
