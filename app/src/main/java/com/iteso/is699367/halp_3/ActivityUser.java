package com.iteso.is699367.halp_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ActivityUser extends AppCompatActivity {

    ImageView picture;
    TextView name;
    TextView email;
    TextView school;
    TextView career;
    TextView grade;
    TextView birthday;
    FirebaseAuth mAuth;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        picture = findViewById(R.id.UserPic);
        name = findViewById(R.id.UserName);
        email = findViewById(R.id.UserEmail);
        school = findViewById(R.id.UserSchool);
        career = findViewById(R.id.UserCarreer);
        grade = findViewById(R.id.UserGrade);
        birthday = findViewById(R.id.UserBirthDay);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference databaseReference = firebaseDatabase.child("users");
        DatabaseReference tempDBR = databaseReference.child(user.getUid());

        Picasso.get().load(user.getPhotoUrl()).into(picture);

        tempDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.child("name").getValue(String.class));
                email.setText(user.getEmail());
                school.setText(dataSnapshot.child("school").getValue(String.class));
                career.setText(dataSnapshot.child("carreer").getValue(String.class));
                grade.setText(dataSnapshot.child("grade").getValue(String.class));
                birthday.setText(dataSnapshot.child("birthday").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
