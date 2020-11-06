package com.tolaotesanya.kindeed.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolaotesanya.kindeed.adapters.ShopAdapter;
import com.tolaotesanya.kindeed.databinding.FragmentKindeedBinding;
import com.tolaotesanya.kindeed.modellayer.model.Product;
import com.tolaotesanya.kindeed.viewmodel.KindeedViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class KindeedFragment extends Fragment implements ShopAdapter.MainInterface {

    FragmentKindeedBinding fragmentKindeedBinding;
    private ShopAdapter adapter;
    private KindeedViewModel viewModel;

    public KindeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentKindeedBinding = FragmentKindeedBinding.inflate(inflater, container, false);
        return fragmentKindeedBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ShopAdapter(this);
        fragmentKindeedBinding.shareFoodPager.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(KindeedViewModel.class);
        viewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.submitList(products);
            }
        });

    }

    @Override
    public void addItem(Product product) {

    }

    @Override
    public void onItemClick(Product product) {

    }
}
