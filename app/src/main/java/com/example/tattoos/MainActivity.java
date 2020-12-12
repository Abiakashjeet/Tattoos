package com.example.tattoos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   EditText Email, Password;
   Button Login, Registration, Text_Result, Text2Result;

    FirebaseAuth fAuth;

    String email;
    String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.login);
        Registration = (Button) findViewById(R.id.registration);
        Text_Result=(Button)findViewById(R.id.text_result);
        Text2Result=(Button)findViewById(R.id.text2result);

        Text_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Data.class);

                startActivity(intent);
            }
        });

        Text2Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,PData.class);

                startActivity(intent);
            }
        });

        fAuth = FirebaseAuth.getInstance();



        Registration.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               email= Email.getText().toString().trim();
               pass= Password.getText().toString().trim();

                fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this, HomePage.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });


    }
}