package ir.rayapars.consultation.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.AboutMessage;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentFeedbackBinding;
import ir.rayapars.consultation.dialogFragment.MessageDialog;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeadBackFragment extends Fragment {

    View v;
    public String type = "0";
    ProgressDialogFragment progressDialog;
    FragmentFeedbackBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentFeedbackBinding.inflate(getLayoutInflater());
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

                    binding.edtDesc.setHint(R.string.txt_suggest);
                }


            }
        });

        binding.btnCriticism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.btnCriticism.isChecked()) {

                    binding.btnSuggest.setTextColor(Color.BLACK);
                    binding.btnCriticism.setTextColor(Color.WHITE);

                    binding.edtDesc.setHint(R.string.txt_criticize);

                }
            }
        });

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (binding.btnSuggest.isChecked()) {

                    type = "0";
                } else if (binding.btnCriticism.isChecked()) {

                    type = "1";
                }

                if (binding.edtDesc.getText().toString().trim().length() > 0) {

                    suggest();

                } else {
                    Toast.makeText(getContext(), "لطفا متن خود را وارد کنید.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;


    }

    public void suggest() {

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        RetrofitClient retrofitClient = App.getRetrofit().create(RetrofitClient.class);
        Call<AboutMessage> call = retrofitClient.suggest(App.KEY, binding.edtDesc.getText().toString(), type);
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
