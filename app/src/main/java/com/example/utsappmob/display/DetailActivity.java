package com.example.utsappmob.display;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.utsappmob.API.APIConfig;
import com.example.utsappmob.API.APIService;
import com.example.utsappmob.R;
import com.example.utsappmob.data.GithubUser;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBarDetail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        progressBarDetail = findViewById(R.id.progressBarDetail);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String username = extras.getString("usernameDetail");
            APIService apiService = APIConfig.getApiService();
            Call<GithubUser> userCall = apiService.getUser(username);

            TextView nameDetail = findViewById(R.id.namaDetail);
            TextView usernameDetail = findViewById(R.id.usernameDetail);
            TextView bioDetail = findViewById(R.id.bioDetail);
            ImageView avatarDetail = findViewById(R.id.avatarDetail);

            showLoading(true);
            userCall.enqueue(new Callback<GithubUser>() {

                public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                    if (response.isSuccessful()){
                        showLoading(false);
                        GithubUser user = response.body();
                        if (user != null){
                            String usernames = "Username : " + user.getUsername();
                            String name = "Name : " + user.getName();
                            String bio = "Bio : " + user.getBio();
                            String avatar = user.getAvatarUrl();
                            usernameDetail.setText(usernames);
                            nameDetail.setText(name);
                            bioDetail.setText(bio);
                            Picasso.get().load(avatar).into(avatarDetail);
                        }else {
                            Toast.makeText(DetailActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GithubUser> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBarDetail.setVisibility(View.VISIBLE);
        } else {
            progressBarDetail.setVisibility(View.GONE);
        }
    }
}