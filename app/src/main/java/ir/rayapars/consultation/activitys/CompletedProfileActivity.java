package ir.rayapars.consultation.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ir.rayapars.consultation.R;

public class CompletedProfileActivity extends AppCompatActivity {

    TextView txtsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_profile);

        txtsave = (TextView) findViewById(R.id.txtsave);

        txtsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CompletedProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
