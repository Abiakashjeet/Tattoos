package com.example.tattoos.api_interfaces;


import com.example.tattoos.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @POST("post")
    Call<Post> createPost(@Body Post post);
}
