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
import com.iteso.is699367.halp_3.beans.Tasks;

public class ActivityAddTask extends AppCompatActivity {

    DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    DatabaseReference mUserTasks = firebaseDatabase.child("users").child(user.getUid()).child("assignments");

    EditText name;
    EditText clas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button button = findViewById(R.id.addTaskButton);
        name = findViewById(R.id.addTaskName);
        clas = findViewById(R.id.addTaskClass);
        final DatePicker duedate = findViewById(R.id.addTaskDuedate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String taskId = mUserTasks.push().getKey();
                // creating user object
                Tasks task = new Tasks(name.getText().toString(), String.valueOf(duedate.getDayOfMonth()+"/"+duedate.getMonth()+"/"+duedate.getYear()), "false", clas.getText().toString());

                mUserTasks.child(taskId).setValue(task);

                finish();
            }
        });
    }
}
