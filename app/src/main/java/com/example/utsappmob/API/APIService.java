package com.example.utsappmob.API;

import com.example.utsappmob.data.GithubUser;
import com.example.utsappmob.data.GithubUserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("search/users")
    Call<GithubUserResponse> searchUsers (@Query("q") String query);

    @GET("users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);
}
