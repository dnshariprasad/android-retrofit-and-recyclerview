package com.retrofitandrecyclerview;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String TAG = "MainActivity";
    private List<ContactModel> contactModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkHandler.getInstance().contacts().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    contactModels = new ArrayList<>();
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
                    ContactsAdapter.build(MainActivity.this, (RecyclerView) findViewById(R.id.rv_contact), contactModels);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<ContactModel> newContactModels = new ArrayList<>();
        for (ContactModel contactModel : contactModels) {
            if (contactModel.contains(newText))
                newContactModels.add(contactModel);
        }
        ContactsAdapter.build(MainActivity.this, (RecyclerView) findViewById(R.id.rv_contact), newContactModels);
        return false;
    }
}
