package com.example.utsappmob.data;

import com.google.gson.annotations.SerializedName;

public class GithubUser{

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("bio")
	private String bio;

	@SerializedName("login")
	private String username;

	@SerializedName("email")
	private String email;

	public String getAvatarUrl(){
		return avatarUrl;
	}
	public String getUsername() {  return username;}
	public String getName(){
		return name;
	}
	public String getBio(){
		return bio;
	}
	public String getEmail(){
		return email;
	}
}