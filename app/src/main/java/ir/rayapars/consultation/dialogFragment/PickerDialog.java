package ir.rayapars.consultation.dialogFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.databinding.DialogPickerBinding;

public class PickerDialog extends DialogFragment {

    View x;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final DialogPickerBinding binding = DialogPickerBinding.inflate(getLayoutInflater());
        x = binding.getRoot();
        x.setClickable(true);
        x.setFocusable(true);

        binding.datePicker.setMinValue(0);
        binding.datePicker.setMaxValue(2);
        binding.datePicker.setDisplayedValues(new String[]{"Belgium", "France", "United Kingdom"});

        binding.btnAccept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(x.getContext(), binding.datePicker.getValue() + "", Toast.LENGTH_SHORT).show();

            }
        });


        return x;
    }
}
