package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.classes.cities;
import ir.rayapars.consultation.databinding.FragmentRewardCodeBinding;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
