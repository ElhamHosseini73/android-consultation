package ir.rayapars.consultation.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.List;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.IntroShow;
import ir.rayapars.consultation.classes.UserInfo;

public class SplashActivity extends AppCompatActivity {


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


                List<IntroShow> listInstal = IntroShow.listAll(IntroShow.class);
                List<UserInfo> infoList = IntroShow.listAll(UserInfo.class);

                if (listInstal.size() > 0) {

                    if (infoList.size() > 0) {

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(SplashActivity.this, CompletedProfileActivity.class);
                        startActivity(intent);
                    }

                } else {

                    Intent intent = new Intent(SplashActivity.this, Intro.class);
                    startActivity(intent);
                }

                finish();

            }
        }, 4000);
    }
}
