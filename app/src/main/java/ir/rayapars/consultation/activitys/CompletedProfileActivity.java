package ir.rayapars.consultation.activitys;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.AboutMessage;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.Login;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.classes.UserInfo;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import ir.rayapars.consultation.fragments.RuleFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedProfileActivity extends AppCompatActivity {

    TextView txtsave, txt_rule;
    EditText edtName, edtFamily, edtPhoneNumber, edtConsultName;
    CheckBox checkbox;
    ProgressDialogFragment progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_profile);

        txtsave = (TextView) findViewById(R.id.txtsave);
        txt_rule = (TextView) findViewById(R.id.txt_rule);
        edtName = (EditText) findViewById(R.id.edtName);
        edtFamily = (EditText) findViewById(R.id.edtFamily);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        edtConsultName = (EditText) findViewById(R.id.edtConsultName);

        checkbox = (CheckBox) findViewById(R.id.checkbox);

        txt_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.face, new RuleFragment()).addToBackStack("").commit();

            }
        });

        txtsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtName.getText().toString().trim().length() > 0 && edtFamily.getText().toString().trim().length() > 0 && edtPhoneNumber.getText().toString().trim().length() > 0 && checkbox.isChecked()) {

                    updateLogin();

                } else {

                    Toast.makeText(CompletedProfileActivity.this, "فیلدهای  ضروری را تکمیل کنید.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void updateLogin() {

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(CompletedProfileActivity.this.getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<Login> call = getData.Login(App.KEY, edtName.getText().toString(), edtFamily.getText().toString(), edtConsultName.getText().toString(), edtPhoneNumber.getText().toString(), "");

        call.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        UserInfo userInfo = new UserInfo();
                        userInfo.uid = response.body().user_info.uid;
                        userInfo.MDU = response.body().user_info.MDU;
                        userInfo.active = response.body().user_info.active;
                        userInfo.image_url = response.body().user_info.image_url;
                        userInfo.first_name = response.body().user_info.first_name;
                        userInfo.last_name = response.body().user_info.last_name;
                        userInfo.mobile = response.body().user_info.mobile;
                        userInfo.email = response.body().user_info.email;
                        userInfo.save();

                        Toast.makeText(CompletedProfileActivity.this, "" + response.body().message, Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                        Intent intent = new Intent(CompletedProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {

                        Toast.makeText(CompletedProfileActivity.this, response.body().message + "", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                } else {

                    Toast.makeText(CompletedProfileActivity.this, "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(CompletedProfileActivity.this, "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
