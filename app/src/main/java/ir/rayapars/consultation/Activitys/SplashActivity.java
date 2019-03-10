package ir.rayapars.consultation.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import ir.rayapars.consultation.R;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //dont show wifi && blutooth
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                sp = getSharedPreferences("sp", 0);

                //Installation app
                String is_Installation = sp.getString("Installation", "false");

                if (is_Installation.equals("false")) {

                    Intent intent = new Intent(SplashActivity.this, Intro.class);
                    startActivity(intent);
                }
                if (is_Installation.equals("true")) {

                    // Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    // startActivity(intent);

                }

                finish();

            }
        }, 4000);
    }
}
