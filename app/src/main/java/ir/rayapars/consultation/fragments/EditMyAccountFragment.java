package ir.rayapars.consultation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.activitys.CompletedProfileActivity;
import ir.rayapars.consultation.activitys.MainActivity;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.Login;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.classes.UserInfo;
import ir.rayapars.consultation.databinding.FragmentEditAccountBinding;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMyAccountFragment extends Fragment {

    View v;
    FragmentEditAccountBinding binding;
    ProgressDialogFragment progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = FragmentEditAccountBinding.inflate(getLayoutInflater());

        v = binding.getRoot();
        v.setClickable(true);
        v.setFocusable(true);

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        customersInfo();

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.edtName.getText().toString().trim().length() > 0 && binding.edtFamily.getText().toString().trim().length() > 0) {
                    updatecustomer();

                } else {

                    Toast.makeText(getContext(), "لطفا فیلد ضروری را تکمیل کنید. ", Toast.LENGTH_SHORT).show();
                }

            }
        });

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

                        binding.edtName.setText(response.body().user_info.first_name + "");
                        binding.edtFamily.setText(response.body().user_info.last_name + "");
                        binding.edtMobile.setText(response.body().user_info.mobile + "");
                        binding.edtAddress.setText(response.body().user_info.address + "");
                        binding.edtIntroducer.setText(response.body().user_info.email + "");

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

    public void updatecustomer() {

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        List<UserInfo> ll = UserInfo.listAll(UserInfo.class);
        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<Login> call = getData.updateCustomer(App.KEY, binding.edtName.getText().toString(), binding.edtFamily.getText().toString(), binding.edtIntroducer.getText().toString(), ll.get(0).uid, ll.get(0).MDU, binding.edtAddress.getText().toString());

        call.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        List<UserInfo> listFace = UserInfo.listAll(UserInfo.class);
                        List<UserInfo> listNew;

                        listNew = listFace;

                        UserInfo.deleteAll(UserInfo.class);

                        UserInfo userInfo = new UserInfo();
                        userInfo.uid = listNew.get(0).uid;
                        userInfo.MDU = listNew.get(0).MDU;
                        userInfo.active = response.body().user_info.active;
                        userInfo.image_url = response.body().user_info.image_url;
                        userInfo.first_name = response.body().user_info.first_name;
                        userInfo.last_name = response.body().user_info.last_name;
                        userInfo.mobile = response.body().user_info.mobile;
                        userInfo.email = response.body().user_info.email;
                        userInfo.address = response.body().user_info.address;
                        userInfo.save();

                        Toast.makeText(getContext(), "" + response.body().message, Toast.LENGTH_SHORT).show();

                        getTargetFragment().onActivityResult(getTargetRequestCode(), 300, null);

                        progressDialog.dismiss();

                        getFragmentManager().popBackStack();

                    } else {

                        Toast.makeText(getContext(), response.body().message + "", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                } else {

                    Toast.makeText(getContext(), "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
