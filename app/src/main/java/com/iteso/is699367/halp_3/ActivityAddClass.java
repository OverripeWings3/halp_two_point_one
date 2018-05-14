package com.iteso.is699367.halp_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iteso.is699367.halp_3.beans.Classes;
import com.iteso.is699367.halp_3.beans.Tasks;

public class ActivityAddClass extends AppCompatActivity {
    DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    DatabaseReference mUserClasses = firebaseDatabase.child("users").child(user.getUid()).child("classes");

    EditText name;
    EditText room;
    EditText teacher;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        button = findViewById(R.id.add_class_button);
        name = findViewById(R.id.addClassName);
        room = findViewById(R.id.addClassRoom);
        teacher = findViewById(R.id.addClassTeacher);
        final TimePicker time = findViewById(R.id.addClassTime);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String classId = mUserClasses.push().getKey();
                // creating user object
                Classes classes = new Classes(name.getText().toString(), room.getText().toString(), teacher.getText().toString(),String.valueOf(time.getHour()+":"+time.getMinute()));

                mUserClasses.child(classId).setValue(classes);

                finish();
            }
        });
    }
}
