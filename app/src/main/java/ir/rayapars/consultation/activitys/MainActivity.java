package ir.rayapars.consultation.activitys;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.adapters.MenuAdapter;
import ir.rayapars.consultation.classes.MenuClass;
import ir.rayapars.consultation.fragments.MainFragment;
import ir.rayapars.consultation.R;

public class MainActivity extends AppCompatActivity {

    ImageView imgMenu;
    DrawerLayout activityMain;
    RecyclerView recycleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgMenu = (ImageView) findViewById(R.id.menu);
        activityMain = (DrawerLayout) findViewById(R.id.activity_main);
        recycleMenu = (RecyclerView) findViewById(R.id.recycleMenu);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameMain, new MainFragment()).commit();

        imgMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                imgMenu.animate().rotation(180).start();

                if (activityMain.isDrawerOpen(Gravity.RIGHT)) {

                    activityMain.closeDrawer(Gravity.RIGHT);

                } else {

                    activityMain.openDrawer(Gravity.RIGHT);
                }

                List<MenuClass> list = new ArrayList<>();

                list.add(new MenuClass("1", "حساب کاربری", R.drawable.ic_person_black_24dp));
                list.add(new MenuClass("2", "معرفی به دوستان", R.drawable.ic_card_giftcard_black_24dp));
                list.add(new MenuClass("3", "شرایط و قوانین", R.drawable.ic_check_black_24dp));
                list.add(new MenuClass("4", "گزارش تخلفات", R.drawable.ic_description_black_24dp));
                list.add(new MenuClass("5", "انتقادات و پیشنهادات", R.drawable.ic_email));
                list.add(new MenuClass("6", "همکاری با ما", R.drawable.ic_star_black_24dp));
                list.add(new MenuClass("7", "تماس با ما", R.drawable.ic_call));
                list.add(new MenuClass("8", "درباره ما", R.drawable.ic_help_black_24dp));
                list.add(new MenuClass("9", "خروج", R.drawable.ic_exit_to_app_black_24dp));

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                MenuAdapter menuAdapter = new MenuAdapter((AppCompatActivity) MainActivity.this, list, activityMain);

                recycleMenu.setLayoutManager(linearLayoutManager);
                recycleMenu.setAdapter(menuAdapter);
            }
        });

    }
}
