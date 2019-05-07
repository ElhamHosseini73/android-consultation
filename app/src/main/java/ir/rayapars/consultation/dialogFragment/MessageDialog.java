package ir.rayapars.consultation.dialogFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.rayapars.consultation.databinding.DialogMessageBinding;

public class MessageDialog extends DialogFragment {

    View x;
    public String MessageDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final DialogMessageBinding binding = DialogMessageBinding.inflate(getLayoutInflater());
        x = binding.getRoot();
        x.setClickable(true);
        x.setFocusable(true);

        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

            }
        });

        binding.message.setText(MessageDialog + "");
        return x;
    }
}
