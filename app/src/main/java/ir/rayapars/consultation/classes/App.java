package ir.rayapars.consultation.classes;

import android.content.Context;

import com.orm.SugarApp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends SugarApp {

    public static Retrofit retrofit;
    private static final String BASE_URL = "http://www.khanehravanshenasi.ir/MoshaveranPanel/webservice/";
    public static final String KEY = "_Xs1r3kn34n65j4bolc46v4Kc56fd8";
    public static volatile Context applicationContext;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static Retrofit getRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).
                        connectTimeout(60, TimeUnit.SECONDS).build();

        if (retrofit == null) {

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    @Override
    public void onCreate() {

        applicationContext = getApplicationContext();
        context = this;

        super.onCreate();

    }

}
