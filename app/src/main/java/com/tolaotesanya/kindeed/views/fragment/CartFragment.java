package com.tolaotesanya.kindeed.views.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.adapters.CartAdapter;
import com.tolaotesanya.kindeed.databinding.FragmentCartBinding;
import com.tolaotesanya.kindeed.modellayer.model.CartItem;
import com.tolaotesanya.kindeed.viewmodel.KindeedViewModel;

import java.util.List;

public class CartFragment extends Fragment {
    private static final String TAG = "CartFragment";

    private FragmentCartBinding fragmentCartBinding;

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

        CartAdapter adapter = new CartAdapter();
        fragmentCartBinding.cartRecyclerView.setAdapter(adapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));

        KindeedViewModel viewModel = new ViewModelProvider(requireActivity()).get(KindeedViewModel.class);
        viewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                adapter.submitList(cartItems);
            }
        });

    }
}
