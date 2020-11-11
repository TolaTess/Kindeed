package com.tolaotesanya.kindeed.views.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.databinding.FragmentOrderBinding;
import com.tolaotesanya.kindeed.viewmodel.KindeedViewModel;

public class OrderFragment extends Fragment {

    private NavController navController;
    private FragmentOrderBinding fragmentOrderBinding;
    private KindeedViewModel kindeedViewModel;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentOrderBinding = FragmentOrderBinding.inflate(inflater, container, false);
        return fragmentOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        kindeedViewModel = new ViewModelProvider(requireActivity()).get(KindeedViewModel.class);
        fragmentOrderBinding.continueShoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reset Cart
                kindeedViewModel.resetCart();
                navController.navigate(R.id.action_orderFragment_to_kindeedFragment);
            }
        });

        fragmentOrderBinding.continueProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reset Cart
                kindeedViewModel.resetCart();
                navController.navigate(R.id.action_orderFragment_to_accountActivity);
            }
        });
    }
}
