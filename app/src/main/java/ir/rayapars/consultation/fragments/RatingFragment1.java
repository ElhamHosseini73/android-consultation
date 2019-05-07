package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.Appointment;
import ir.rayapars.consultation.databinding.FragmentRating1Binding;

public class RatingFragment1 extends Fragment {

    View v;
    public List<Appointment> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final FragmentRating1Binding binding = FragmentRating1Binding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setClickable(true);
        v.setFocusable(true);


        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.edtName.getText().toString().trim().length() == 0 || binding.edtFamily.getText().toString().trim().length() == 0 || binding.edtPhoneNumber.getText().toString().trim().length() == 0 || binding.edtEmail.getText().toString().trim().length() == 0) {

                    Toast.makeText(getContext(), "اطلاعات مورد نظر را کامل کنید.", Toast.LENGTH_SHORT).show();

                } else {

                    list.get(0).fname = binding.edtName.getText().toString();
                    list.get(0).lname = binding.edtFamily.getText().toString();
                    list.get(0).mobile = binding.edtPhoneNumber.getText().toString();
                    list.get(0).email = binding.edtEmail.getText().toString();
                    FragmentTransaction transaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction2.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    RequestRatingFragment requestRatingFragment = new RequestRatingFragment();
                    requestRatingFragment.list = list;
                    transaction2.replace(R.id.frameMain, requestRatingFragment).commit();

                }
            }
        });

        return v;
    }


}
