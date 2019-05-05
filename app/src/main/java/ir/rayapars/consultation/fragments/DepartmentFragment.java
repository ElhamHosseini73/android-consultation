package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import ir.rayapars.consultation.adapters.DepartmentAdapter;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.BlogCat;
import ir.rayapars.consultation.classes.BlogCatList;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentDepartmentBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentFragment extends Fragment {

    View x;
    ProgressDialogFragment progressDialog;
    int page = 1, perPage = 10;
    FragmentDepartmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentDepartmentBinding.inflate(getLayoutInflater());
        x = binding.getRoot();
        x.setFocusable(true);
        x.setClickable(true);

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        blogCats();

        return x;
    }

    public void blogCats() {

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<BlogCatList> call = getData.blogCatsList(App.KEY);
        call.enqueue(new Callback<BlogCatList>() {
            @Override
            public void onResponse(Call<BlogCatList> call, Response<BlogCatList> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        progressDialog.dismiss();

                        List<BlogCat> list = new ArrayList<>();

                        for (int i = 0; i < response.body().cats.size(); i++) {

                            list.add(response.body().cats.get(i));

                        }

                        GridLayoutManager manager = new GridLayoutManager(x.getContext(), 3);
                        DepartmentAdapter adapter = new DepartmentAdapter(list, (AppCompatActivity) x.getContext());

                        binding.recyclerView.setLayoutManager(manager);
                        binding.recyclerView.setAdapter(adapter);

                    } else {

                        Toast.makeText(x.getContext(), response.body().message + "", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                } else {

                    Toast.makeText(x.getContext(), "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<BlogCatList> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(x.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });


    }


}
