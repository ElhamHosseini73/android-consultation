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

import ir.rayapars.consultation.classes.AboutMessage;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentInfractionsBinding;
import ir.rayapars.consultation.dialogFragment.MessageDialog;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfractionsFragment extends Fragment {


    View v;
    FragmentInfractionsBinding binding;
    ProgressDialogFragment progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentInfractionsBinding.inflate(getLayoutInflater());

        v = binding.getRoot();
        v.setClickable(true);
        v.setFocusable(true);

        binding.toolbar.imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().popBackStack();
            }
        });

        binding.toolbar.txtPageName.setText("گزارش تخلف ");

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.edtName.getText().toString().trim().length() > 0 && binding.edtFamily.getText().toString().trim().length() > 0 && binding.edtPhoneNumber.getText().toString().trim().length() > 0 && binding.edtConsultName.getText().toString().trim().length() > 0 && binding.edtDesc.getText().toString().trim().length() > 0) {

                    report();

                } else {

                    Toast.makeText(getContext(), "لطفا اطلاعات خود را کامل کنید.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    public void report() {

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        RetrofitClient retrofitClient = App.getRetrofit().create(RetrofitClient.class);
        Call<AboutMessage> call = retrofitClient.report(App.KEY, binding.edtName.getText().toString(), binding.edtFamily.getText().toString(), binding.edtDesc.getText().toString(), binding.edtPhoneNumber.getText().toString(), binding.edtConsultName.getText().toString());
        call.enqueue(new Callback<AboutMessage>() {
            @Override
            public void onResponse(Call<AboutMessage> call, Response<AboutMessage> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {


                        progressDialog.dismiss();
                        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                        MessageDialog fragment = new MessageDialog();
                        fragment.MessageDialog = response.body().message;
                        fragment.show(fragmentManager, "dialog");

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
}
