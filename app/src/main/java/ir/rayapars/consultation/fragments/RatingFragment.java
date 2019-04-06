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
import ir.rayapars.consultation.databinding.FragmentRatingBinding;

public class RatingFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentRatingBinding binding = FragmentRatingBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setClickable(true);
        v.setFocusable(true);


        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction2.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                transaction2.replace(R.id.frameMain, new RatingFragment1()).commit();

            }
        });
        return v;
    }
}
