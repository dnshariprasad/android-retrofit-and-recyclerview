package com.retrofitandrecyclerview;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Hari Prasad on 10/30/16.
 */

public interface Api {
    String BASE_URL = "http://api.androidhive.info";

    @GET("/contacts")
    Call<String> contacts();
}
