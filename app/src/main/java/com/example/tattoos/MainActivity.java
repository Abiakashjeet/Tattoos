package com.example.tattoos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public EditText UserName, Password;
    public Button Login, Registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserName =(EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.login);
        Registration= (Button) findViewById(R.id.registration);

    Login.setOnClickListener((view) -> {
        String uemail = UserName.getText().toString().trim();
        String upassword = Password.getText().toString().trim();

        if(upassword.length() < 6){
            Password.setError("Password must be more then 6 characters");
            return;
        }
        startActivity(new Intent(getApplicationContext(),HomePage.class));
    });

    Registration.setOnClickListener((view) -> {
        startActivity(new Intent(getApplicationContext(),Registration.class));
    });

    }
}