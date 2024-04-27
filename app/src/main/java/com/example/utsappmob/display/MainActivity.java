package com.example.utsappmob.display;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.utsappmob.API.APIConfig;
import com.example.utsappmob.API.APIService;
import com.example.utsappmob.R;
import com.example.utsappmob.data.GithubUser;
import com.example.utsappmob.data.GithubUserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUser;
    private ProgressBar progressBar;
    private GithubUserAdapter adapter;
    private List<GithubUser> userList = new ArrayList<>();

    private APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUser = findViewById(R.id.rvUser);
        progressBar = findViewById(R.id.progressBar);

        apiService = APIConfig.getApiService();

        searchUsers("Risqi" + "" + "" + "r");
    }

    private void searchUsers(String query) {
        progressBar.setVisibility(View.VISIBLE);

        Call<GithubUserResponse> call = apiService.searchUsers(query);

        call.enqueue(new Callback<GithubUserResponse>() {
            @Override
            public void onResponse(Call<GithubUserResponse> call, Response<GithubUserResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    userList.clear();
                    userList.addAll(response.body().getUsers());
                    showRecyclerView();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GithubUserResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showRecyclerView() {
        if (adapter == null) {
            adapter = new GithubUserAdapter(userList);
            rvUser.setAdapter(adapter);
            rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}