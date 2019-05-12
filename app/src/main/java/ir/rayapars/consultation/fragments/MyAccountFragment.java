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
import ir.rayapars.consultation.classes.AboutMessage;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.Login;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.classes.UserInfo;
import ir.rayapars.consultation.databinding.FragmentMyAccountBinding;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccountFragment extends Fragment {

    View v;
    ProgressDialogFragment progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentMyAccountBinding binding = FragmentMyAccountBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setFocusable(true);
        v.setClickable(true);

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                transaction.add(R.id.frameMain, new EditMyAccountFragment()).addToBackStack("").commit();

            }
        });

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        customersInfo();

        return v;
    }

    public void customersInfo() {

        List<UserInfo> userInfos = UserInfo.listAll(UserInfo.class);

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<Login> call = getData.customersInfo(App.KEY, userInfos.get(0).uid, userInfos.get(0).MDU);

        call.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        Toast.makeText(getContext(), "" + response.body().status, Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();


                    } else {

                        Toast.makeText(v.getContext(), response.body().message + "", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                } else {

                    Toast.makeText(v.getContext(), "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(v.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
