package com.retrofitandrecyclerview;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hari Prasad on 10/30/16.
 */
public class NetworkHandler {
    private static NetworkHandler ourInstance = new NetworkHandler();

    public static NetworkHandler getInstance() {
        return ourInstance;
    }

    private NetworkHandler() {
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private Api api = retrofit.create(Api.class);

    public Call<String> contacts() {
        return api.contacts();
    }
}
