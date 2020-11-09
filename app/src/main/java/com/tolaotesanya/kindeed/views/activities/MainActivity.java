package com.tolaotesanya.kindeed.views.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    private NavController navController;
    private KindeedViewModel viewModel;
    private int cartQuantity = 0;

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
                int quantity = 0;
                for(CartItem cartItem: cartItems){
                    quantity += cartItem.getQuantity();
                }
                cartQuantity = quantity;
                //call to draw menu again
                invalidateOptionsMenu();
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
        //get badge
        MenuItem menuItem = menu.findItem(R.id.cartFragment);
        View actionView = menuItem.getActionView();
        TextView cartBadgeTextView = actionView.findViewById(R.id.cart_badge_text);
        cartBadgeTextView.setText(String.valueOf(cartQuantity));
        //remove textView if quantity is 0
        cartBadgeTextView.setVisibility(cartQuantity == 0 ? View.GONE : View.VISIBLE);

        //Navigate to the cart fragment
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
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
