package com.tolaotesanya.kindeed.views.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.adapters.CartAdapter;
import com.tolaotesanya.kindeed.databinding.FragmentCartBinding;
import com.tolaotesanya.kindeed.modellayer.model.CartItem;
import com.tolaotesanya.kindeed.viewmodel.KindeedViewModel;

import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.CartInterface {
    private static final String TAG = "CartFragment";

    private FragmentCartBinding fragmentCartBinding;
    private KindeedViewModel viewModel;
    private NavController navController;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        CartAdapter adapter = new CartAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(adapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));

        viewModel = new ViewModelProvider(requireActivity()).get(KindeedViewModel.class);
        viewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                adapter.submitList(cartItems);
                fragmentCartBinding.placeOrderBtn.setEnabled(cartItems.size() > 0);
            }
        });

        viewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.orderTotalText.setText("Total: " + aDouble.toString() + " EUR");

            }
        });

        fragmentCartBinding.placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_cartFragment_to_orderFragment);
            }
        });

    }

    @Override
    public void deleteItem(CartItem cartItem) {
        viewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        viewModel.changeQuantity(cartItem, quantity);
    }
}
