package com.example.tattoos;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.HashMap;
import java.util.Map;

public class HomePage extends AppCompatActivity {
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.model1, R.drawable.model2, R.drawable.model3, R.drawable.model4};
    TextView toolbar;
    Button Submit;
    EditText Comment;
    FirebaseFirestore firebaseFirestore;
    DocumentReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        toolbar = findViewById(R.id.toolbar);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        ImageListener imageListener = null;
        carouselView.setImageListener(imageListener);
        firebaseFirestore = FirebaseFirestore.getInstance();
        ref = firebaseFirestore.collection("comments").document();

        Comment = findViewById(R.id.comment);
        Submit = findViewById(R.id.btn_submit);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Comment.getText().toString().equals("")) {
                    Toast.makeText(HomePage.this, "Please input", Toast.LENGTH_LONG).show();
                } else {
                    add_comment();

                }
            }
        });

    }

    public void add_comment() {
        {
            ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {


                    Map<String, Object> reg_entry = new HashMap<>();
                    reg_entry.put("Comment", Comment.getText().toString());
                    firebaseFirestore.collection("comments")
                            .add(reg_entry)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(HomePage.this, "Comment Successfully added", Toast.LENGTH_SHORT).show();
                                    Comment.setText("");

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("Error", e.getMessage());
                                }
                            });
                    ImageListener imageListener = new ImageListener() {
                        @Override
                        public void setImageForPosition(int position, ImageView imageView) {
                            imageView.setImageResource(sampleImages[position]);
                        }
                    };
                }

            });

        }
    }
}