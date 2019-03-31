package ir.rayapars.consultation.activitys;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.rayapars.consultation.adapters.IntroAdapter;
import ir.rayapars.consultation.R;

public class Intro extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    ImageView nextStep;
    int[] layouts;
    TextView[] dots;
    IntroAdapter introAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        nextStep = (ImageView) findViewById(R.id.nextStep);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.info1,
                R.layout.info2,
                R.layout.info3};

        // adding bottom dots
        addBottomDots(0);

        introAdapter = new IntroAdapter(Intro.this, layouts);
        viewPager.setAdapter(introAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {

                } else {

                    // move to next screen
                    startActivity(new Intent(Intro.this, CompletedProfileActivity.class));
                    finish();
                }
            }
        });
    }

    private void addBottomDots(int currentPage) {

        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                nextStep.setImageResource(R.drawable.ic_tik);
                nextStep.setVisibility(View.VISIBLE);

            } else {
                nextStep.setVisibility(View.GONE);
                nextStep.setImageResource(R.drawable.ic_next_white);
            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        @Override
        public void onPageScrollStateChanged(int arg0) {


        }
    };


}
