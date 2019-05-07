package ir.rayapars.consultation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.List;

import ir.rayapars.consultation.classes.AboutMessage;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.Appointment;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentRequestRatingBinding;
import ir.rayapars.consultation.dialogFragment.AppointmentDialog;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestRatingFragment extends Fragment {

    View v;
    public List<Appointment> list;
    ProgressDialogFragment progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final FragmentRequestRatingBinding binding = FragmentRequestRatingBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setClickable(true);
        v.setFocusable(true);

        binding.tvName.setText(list.get(0).fname + list.get(0).lname);
        binding.tvPhoneNumber.setText(list.get(0).mobile);
        binding.tvConsultantCase.setText(list.get(0).dtypeName);
        binding.tvConsultantName.setText(list.get(0).nameAdviser);
        binding.tvDate.setText(list.get(0).nameDate);

        binding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.rulesCheckbox.isChecked()) {

                    appointment();

                } else {

                    Toast.makeText(getContext(), "پزیرفتن شرایط و قوانین الزامی است.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return v;
    }

    public void appointment() {

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        RetrofitClient client = App.getRetrofit().create(RetrofitClient.class);
        Call<AboutMessage> call = client.appointment(App.KEY, list.get(0).id, list.get(0).date, list.get(0).type, list.get(0).dtype, list.get(0).fname, list.get(0).lname, list.get(0).mobile, list.get(0).email);
        call.enqueue(new Callback<AboutMessage>() {
            @Override
            public void onResponse(Call<AboutMessage> call, Response<AboutMessage> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                        AppointmentDialog fragment = new AppointmentDialog();
                        fragment.setTargetFragment(RequestRatingFragment.this, 300);
                        fragment.show(fragmentManager, "dialog");

                        progressDialog.dismiss();
                    } else {

                        Toast.makeText(v.getContext(), "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                } else {

                    Toast.makeText(v.getContext(), "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<AboutMessage> call, Throwable t) {


                progressDialog.dismiss();
                Toast.makeText(v.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {

            // getFragmentManager().popBackStack();

        }

    }
}
