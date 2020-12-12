package com.example.tattoos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tattoos.api_interfaces.JsonPlaceHolderApi;
import com.example.tattoos.models.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PData extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_data);

        textViewResult = findViewById(R.id.text_view_result);

        createPost();
    }

    void createPost() {

        Post post = new Post("shivam", "sharma", "shivam@gmail.com", "qwertyuiop");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Abiakashjeet/mockjson/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Post> call = jsonPlaceHolderApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " +response.code());
                }
                Post postresponse=response.body();
                String content = "";
                content += "Fname: "+post.getEmail() + "\n";
                content += "Lname: "+post.getEmail() + "\n";
                content += "Email: "+post.getEmail() + "\n";
                content += "Password"+post.getPassword() + "\n";

                textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }
}