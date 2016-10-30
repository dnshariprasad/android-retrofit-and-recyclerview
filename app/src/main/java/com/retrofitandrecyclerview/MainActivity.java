package com.retrofitandrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkHandler.getInstance().contacts().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    List<ContactModel> contactModels = new ArrayList<>();
                    Log.d(TAG, "onResponse: " + response.raw());
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        JSONArray jsonArray = jsonObject.optJSONArray("contacts");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            contactModels.add(ContactModel.fromJson(jsonArray.optJSONObject(i).toString()));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ConnectionsAdapter.build(MainActivity.this, (RecyclerView) findViewById(R.id.rv_contact), contactModels);
                } else {
                    Log.e(TAG, "onResponse: " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
