package com.example.tattoos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tattoos.models.NewUserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {
    public EditText FirstName, LastName, Email, Password;
    public Button SignUp;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        FirstName = (EditText) findViewById(R.id.firstName_et);
        LastName = (EditText) findViewById(R.id.lastName_et);
        Email = (EditText) findViewById(R.id.email_et);
        Password = (EditText) findViewById(R.id.password);

        SignUp = (Button) findViewById(R.id.signup);

        db = FirebaseFirestore.getInstance();

        SignUp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
               String name = FirstName.getText().toString();
               String lastname = LastName.getText().toString();
               String email = Email.getText().toString();
               String password = Password.getText().toString();



                if (TextUtils.isEmpty(name)) {
                    FirstName.setError("Name is required");
                }

                else if (TextUtils.isEmpty(lastname)) {
                    LastName.setError("Name is required");
                }

                else if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is required");

                }

                else if (TextUtils.isEmpty(password)) {
                    Password.setError("Please enter password");

                }
               else if (password.length() < 6) {
                    Password.setError("Password must be more then 6 characters");
                    return;
                }

                addData(FirstName, LastName, Email, Password);
            }
        });
    }

    public void addData(EditText FirstName, EditText LastName, EditText Email, EditText Password) {

        NewUserDetails userDetails = new NewUserDetails(FirstName,LastName,Email,Password);
        db.collection("UserDetails")

                .add(userDetails)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Successfully Sign Up", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
