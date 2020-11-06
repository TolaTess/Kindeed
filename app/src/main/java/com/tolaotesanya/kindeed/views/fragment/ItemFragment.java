package com.tolaotesanya.kindeed.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolaotesanya.kindeed.databinding.FragmentItemBinding;
import com.tolaotesanya.kindeed.viewmodel.KindeedViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ItemFragment extends Fragment {

    private FragmentItemBinding fragmentItemBinding;
    private KindeedViewModel kindeedViewModel;

    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentItemBinding = FragmentItemBinding.inflate(inflater, container, false);
        return fragmentItemBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        kindeedViewModel = new ViewModelProvider(requireActivity()).get(KindeedViewModel.class);
        fragmentItemBinding.setKindeedViewModel(kindeedViewModel);
    }

}
