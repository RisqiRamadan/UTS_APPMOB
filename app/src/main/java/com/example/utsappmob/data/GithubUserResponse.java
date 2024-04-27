package com.example.utsappmob.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUserResponse {
    @SerializedName("items")
    private List<GithubUser> users;

    public List<GithubUser> getUsers() {
        return users;
    }
    public void setUsers(List<GithubUser> users) {
        this.users = users;
    }
}
