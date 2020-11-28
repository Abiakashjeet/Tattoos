package com.example.tattoos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tattoos.models.Comment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomePage extends AppCompatActivity {
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.model1, R.drawable.model2, R.drawable.model3, R.drawable.model4};

    EditText Review;
    Button SubmitComment;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Review = findViewById(R.id.comment_et);
        SubmitComment = findViewById(R.id.submit_btn);
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        db = FirebaseFirestore.getInstance();

    clkList();
    }
    ImageListener imageListener = (position, imageView) -> imageView.setImageResource(sampleImages[position]);

       public void clkList() {
           SubmitComment.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String cmnt = Review.getText().toString();
                   addData(cmnt);

               }
           });
       }



    public void addData(String review){
        Comment comment=new Comment(review);
        db.collection("Comments")
                .add(comment)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Comment Recorded: ", Toast.LENGTH_LONG).show();
                    }
                });


    }
}