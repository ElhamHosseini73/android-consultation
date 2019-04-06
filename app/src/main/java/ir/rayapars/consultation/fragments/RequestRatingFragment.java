package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.rayapars.consultation.databinding.FragmentRequestRatingBinding;


public class RequestRatingFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentRequestRatingBinding binding = FragmentRequestRatingBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setClickable(true);
        v.setFocusable(true);

        return v;
    }
}
