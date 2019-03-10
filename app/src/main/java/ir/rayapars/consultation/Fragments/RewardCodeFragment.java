package ir.rayapars.consultation.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.rayapars.consultation.databinding.FragmentRewardCodeBinding;

public class RewardCodeFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentRewardCodeBinding binding = FragmentRewardCodeBinding.inflate(getLayoutInflater());

        v = binding.getRoot();
        v.setFocusable(true);
        v.setClickable(true);

        binding.toolbar.txtPageName.setText("دعوت دوستان");

        binding.toolbar.imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().popBackStack();

            }
        });
        return v;

    }
}
