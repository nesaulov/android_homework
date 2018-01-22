package ru.esaulov.retrofit;

/**
 * Created by nesaulov on 23/01/2018.
 */

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {
    private static ApiInterface apiInterface;

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://umorili.herokuapp.com/api/get/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getApi () {
        return apiInterface;
    }
}