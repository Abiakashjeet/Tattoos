package com.example.tattoos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tattoos.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {
    EditText FirstName, LastName, Email,Pwd;
    Button SignUp;
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    String fname;
    String lname;
    String email;
    String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        FirstName =findViewById(R.id.firstName_et);
        LastName =findViewById(R.id.lastName_et);
        Email =findViewById(R.id.email_et);
        Pwd =findViewById(R.id.pwd);

        SignUp =findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

       // if(mAuth.getCurrentUser() !=null){
         //   startActivity(new Intent(getApplicationContext(),MainActivity.class));
           // finish();
        //}

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname=FirstName.getText().toString();
                lname=LastName.getText().toString();
                email=Email.getText().toString();
                pass=Pwd.getText().toString();


                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            addData(fname,lname,email,pass);
                            Toast.makeText(Registration.this,"User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else {
                            Toast.makeText(Registration.this, "Authentication failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });

    }

    public void addData(String firstName, String lastName, String email ,String pass){

        User user= new User(firstName, lastName, email, pass);
        db.collection("users")
               .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>(){

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"User added",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Registration.this,HomePage.class);
                        startActivity(intent);
                    }
                })
              .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

}