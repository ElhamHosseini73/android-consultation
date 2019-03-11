package ir.rayapars.consultation.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.databinding.FragmentFeedbackBinding;

public class FeadBackFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final FragmentFeedbackBinding binding = FragmentFeedbackBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setFocusable(true);
        v.setClickable(true);

        binding.toolbar.imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().popBackStack();
            }
        });

        binding.toolbar.txtPageName.setText("انتقادات و پیشنهادات ");

        binding.btnSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (binding.btnSuggest.isChecked()) {

                    binding.btnSuggest.setTextColor(Color.WHITE);
                    binding.btnCriticism.setTextColor(Color.BLACK);

                    binding.edtDesc.setText(R.string.txt_suggest);
                }


            }
        });

        binding.btnCriticism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.btnCriticism.isChecked()) {

                    binding.btnSuggest.setTextColor(Color.BLACK);
                    binding.btnCriticism.setTextColor(Color.WHITE);

                    binding.edtDesc.setText(R.string.txt_criticize);

                }
            }
        });

        return v;

    }
}
