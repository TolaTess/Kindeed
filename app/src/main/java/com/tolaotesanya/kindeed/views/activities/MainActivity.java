package com.tolaotesanya.kindeed.views.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.modellayer.model.CartItem;
import com.tolaotesanya.kindeed.viewmodel.KindeedViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    NavController navController;
    KindeedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.fragment_main);
        NavigationUI.setupActionBarWithNavController(this, navController);
        viewModel = new ViewModelProvider(this).get(KindeedViewModel.class);
        viewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // for back button
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

//    private void logout() {
//        Intent intent = new Intent(this, AuthActivity.class);
//        intent.putExtra(AuthActivity.EXTRA_CLEAR_CREDENTIALS, true);
//        startActivity(intent);
//        finish();
//    }
}
