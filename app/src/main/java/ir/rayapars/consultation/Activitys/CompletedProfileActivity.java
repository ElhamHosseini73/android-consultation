package ir.rayapars.consultation.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ir.rayapars.consultation.R;

public class CompletedProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_profile);

        Intent intent = new Intent(CompletedProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
