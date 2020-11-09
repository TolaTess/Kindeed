package com.tolaotesanya.kindeed.views.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.databinding.FragmentCartBinding;
import com.tolaotesanya.kindeed.viewmodel.KindeedViewModel;

public class CartFragment extends Fragment {

    FragmentCartBinding fragmentCartBinding;
    KindeedViewModel viewModel;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
