package com.example.tattoos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    public EditText UserName, Password;
    public Button Login, Registration;

    FirebaseFirestore db;
    public  boolean auth=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        UserName =(EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.login);
        Registration= (Button) findViewById(R.id.registration);

        db= FirebaseFirestore.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.login:
                        db.collection("users")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                                if (UserName.getText().toString().equalsIgnoreCase(doc.getString("Email")) & Password.getText().toString().equalsIgnoreCase(doc.getString("Password"))) {
                                                    finish();
                                                    Intent home = new Intent(MainActivity.this, Registration.class);
                                                    startActivity(home);
                                                    auth = true;
                                                    break;

                                                } else
                                                    auth = false;
                                            }
                                            if (!auth)
                                                Toast.makeText(MainActivity.this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                                        }
                                    }


                                });
                }
            }
        });
    }
}