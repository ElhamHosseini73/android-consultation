package ir.rayapars.consultation.dialogFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.Education;
import ir.rayapars.consultation.databinding.DialogPickerBinding;

import static android.app.Activity.RESULT_OK;

public class PickerDialog extends DialogFragment {

    View x;
    public List<Education> listDate;
    public String[] listDateStr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final DialogPickerBinding binding = DialogPickerBinding.inflate(getLayoutInflater());
        x = binding.getRoot();
        x.setClickable(true);
        x.setFocusable(true);

        binding.datePicker.setMinValue(0);
        binding.datePicker.setMaxValue(listDateStr.length - 1);
        binding.datePicker.setDisplayedValues(listDateStr);

        binding.btnAccept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(x.getContext(), binding.datePicker.getValue() + "", Toast.LENGTH_SHORT).show();

            }
        });

        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

            }
        });

        binding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.putExtra("idDate", listDate.get(binding.datePicker.getValue()).val);
                intent.putExtra("nameDate", listDate.get(binding.datePicker.getValue()).name);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

                dismiss();

            }
        });


        return x;
    }
}
