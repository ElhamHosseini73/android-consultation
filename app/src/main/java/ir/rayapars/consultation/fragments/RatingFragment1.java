package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.databinding.FragmentRating1Binding;

public class RatingFragment1 extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentRating1Binding binding = FragmentRating1Binding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setClickable(true);
        v.setFocusable(true);


        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction2.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                transaction2.replace(R.id.frameMain, new RequestRatingFragment()).commit();

            }
        });

        return v;
    }


}
