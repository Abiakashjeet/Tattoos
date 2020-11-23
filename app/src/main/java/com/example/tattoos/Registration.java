package com.example.tattoos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {
    public EditText FirstName, LastName, Email, Password , DateOfBirth, CompanyName;
    public Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FirstName=(EditText) findViewById(R.id.firstName_et);
        LastName=(EditText) findViewById(R.id.lastName_et);
        Email=(EditText) findViewById(R.id.email_et);
        Password=(EditText) findViewById(R.id.password);
        DateOfBirth=(EditText) findViewById(R.id.dob_et);
        CompanyName=(EditText) findViewById(R.id.companyName_et);

        SignUp=(Button) findViewById(R.id.signup);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = FirstName.getText().toString().trim();
                String ulastname = LastName.getText().toString().trim();
                String uemail = Email.getText().toString().trim();
                String upassword = Password.getText().toString().trim();


                if (TextUtils.isEmpty(uname)) {
                    FirstName.setError("Name is required");
                }

                if (TextUtils.isEmpty(ulastname)) {
                    LastName.setError("Name is required");
                }

                if (TextUtils.isEmpty(uemail)) {
                    Email.setError("Email is required");

                }

                if (TextUtils.isEmpty(upassword)) {
                    Password.setError("Please enter password");

                }
                if (upassword.length() < 6) {
                    Password.setError("Password must be more then 6 characters");
                    return;
                }


            }
        });
    }
}