package com.iteso.is699367.halp_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityGetInfo extends AppCompatActivity {

    EditText school;
    EditText grade;
    EditText carreer;
    DatePicker birthday;
    Button done;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_info);

        school = findViewById(R.id.school_name);
        grade = findViewById(R.id.grade);
        carreer = findViewById(R.id.carreer_name);
        birthday = findViewById(R.id.birthday_picker);
        done = findViewById(R.id.done);
        mAuth = FirebaseAuth.getInstance();

        final String bDay = Integer.toString(birthday.getDayOfMonth()) +
                getString(R.string.of) + Integer.toString(birthday.getMonth()) +
                " " + Integer.toString(birthday.getYear());

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(school.getText().equals("")) {
                    school.setError(getString(R.string.error_message));
                }
                else {
                    FirebaseUser mUser = mAuth.getCurrentUser();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                            .getReference().child("users").child(mUser.getUid()).child("school");
                    databaseReference.setValue(school.getText());
                    databaseReference = FirebaseDatabase.getInstance()
                            .getReference().child("users").child(mUser.getUid()).child("carreer");
                    databaseReference.setValue(carreer.getText());
                    databaseReference = FirebaseDatabase.getInstance()
                            .getReference().child("users").child(mUser.getUid()).child("grade");
                    databaseReference.setValue(grade.getText());
                    databaseReference = FirebaseDatabase.getInstance()
                            .getReference().child("users").child(mUser.getUid()).child("birthday");
                    databaseReference.setValue(bDay);
                    Intent intent = new Intent(ActivityGetInfo.this, ActivityMain.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }
}
