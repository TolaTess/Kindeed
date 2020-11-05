package com.tolaotesanya.kindeed.views.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.adapters.CategoryCustomAdapter;
import com.tolaotesanya.kindeed.adapters.ShopAdapter;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.databinding.FragmentKindeedBinding;
import com.tolaotesanya.kindeed.databinding.FragmentMainBinding;
import com.tolaotesanya.kindeed.modellayer.model.Product;

public class KindeedFragment extends Fragment implements ShopAdapter.MainInterface {

    FragmentKindeedBinding fragmentMainBinding;
    private CategoryCustomAdapter adapter;
    private IntentPresenter presenter;

    public KindeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentKindeedBinding.inflate(inflater, container, false);
        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new CategoryCustomAdapter(presenter, getContext());
        fragmentMainBinding.donatePager.setAdapter(adapter);
        fragmentMainBinding.shareFoodPager.setAdapter(adapter);

    }

    @Override
    public void addItem(Product product) {

    }

    @Override
    public void onItemClick(Product product) {

    }
}
