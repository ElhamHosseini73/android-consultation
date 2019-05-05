package ir.rayapars.consultation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.adapters.ConsultantAdapter;
import ir.rayapars.consultation.adapters.DepartmentAdapter;
import ir.rayapars.consultation.adapters.EducationAdapter;
import ir.rayapars.consultation.classes.AdviserDetails;
import ir.rayapars.consultation.classes.AdviserList;
import ir.rayapars.consultation.classes.Advisers;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.BlogCat;
import ir.rayapars.consultation.classes.BlogCatList;
import ir.rayapars.consultation.classes.Education;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentRatingBinding;
import ir.rayapars.consultation.dialogFragment.PickerDialog;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingFragment extends Fragment {

    View v;
    ProgressDialogFragment progressDialog;
    FragmentRatingBinding binding;

    List<String> listBlogCatStr, listAdviserStr;
    String[] listDateStr;

    List<BlogCat> listBlogCat;
    List<Advisers> listAdviser;
    List<Education> listDate;
    int idBlogCatChose, idAdviserChose;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentRatingBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setClickable(true);
        v.setFocusable(true);

        listBlogCatStr = new ArrayList<>();
        listAdviserStr = new ArrayList<>();

        listBlogCat = new ArrayList<>();
        listAdviser = new ArrayList<>();
        listDate = new ArrayList<>();

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction2.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                transaction2.replace(R.id.frameMain, new RatingFragment1()).commit();

            }
        });

        binding.txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listDate.size() > 0 && listAdviserStr.size() > 0) {

                    FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                    PickerDialog fragment = new PickerDialog();
                    fragment.listDate = listDate;
                    fragment.listDateStr = listDateStr;
                    fragment.show(fragmentManager, "dialog");
                }

            }
        });

        binding.catgory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (listBlogCatStr.size() > 0) {

                    idBlogCatChose = position;

                    listAdviserStr = new ArrayList<>();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listAdviserStr);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinneradviser.setAdapter(adapter);

                    AdviserList(listBlogCat.get(position).id);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        binding.spinneradviser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (listAdviserStr.size() > 0) {

                    idAdviserChose = position;
                    listDate = new ArrayList<>();
                    AdviserDetails(listAdviser.get(position).id);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        blogCats();

        return v;

    }

    public void blogCats() {

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<BlogCatList> call = getData.blogCatsList(App.KEY);

        call.enqueue(new Callback<BlogCatList>() {
            @Override
            public void onResponse(Call<BlogCatList> call, Response<BlogCatList> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        progressDialog.dismiss();

                        for (int i = 0; i < response.body().cats.size(); i++) {

                            listBlogCat.add(response.body().cats.get(i));
                            listBlogCatStr.add(response.body().cats.get(i).title);

                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listBlogCatStr);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.catgory.setAdapter(dataAdapter);


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
            public void onFailure(Call<BlogCatList> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(v.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void AdviserList(String catId) {

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        final RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<AdviserList> call = getData.AdviserList(App.KEY, "", "", "1", catId);
        call.enqueue(new Callback<AdviserList>() {
            @Override
            public void onResponse(Call<AdviserList> call, Response<AdviserList> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        progressDialog.dismiss();


                        for (int i = 0; i < response.body().advisers.size(); i++) {

                            listAdviser.add(response.body().advisers.get(i));
                            listAdviserStr.add(response.body().advisers.get(i).name);
                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listAdviserStr);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.spinneradviser.setAdapter(adapter);

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
            public void onFailure(Call<AdviserList> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(v.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void AdviserDetails(String adviserId) {

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<AdviserDetails> call = getData.adviserDetails(App.KEY, adviserId, "1");
        call.enqueue(new Callback<AdviserDetails>() {
            @Override
            public void onResponse(Call<AdviserDetails> call, Response<AdviserDetails> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        progressDialog.dismiss();

                        listDateStr = new String[response.body().adviser.date_range.size()];

                        for (int i = 0; i < response.body().adviser.date_range.size(); i++) {

                            listDate.add(response.body().adviser.date_range.get(i));
                            listDateStr[i] = response.body().adviser.date_range.get(i).name;

                        }

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
            public void onFailure(Call<AdviserDetails> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(v.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 300) {
        }
    }
}
