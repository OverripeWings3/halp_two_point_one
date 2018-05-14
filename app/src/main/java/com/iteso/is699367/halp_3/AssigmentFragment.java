package com.iteso.is699367.halp_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.halp_3.beans.Tasks;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AssigmentFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference firebaseDatabase;
    DatabaseReference mUserTasks;
    FirebaseAuth mAuth;
    ArrayList<Tasks> tasks = new ArrayList<Tasks>();
    int i = 1;
    int numChild =0 ;

    public int getChild(){
        return numChild;
    }


    public AssigmentFragment() {
        // Required empty public constructor
    }

    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_assigments, container, false);
        fab = rootView.findViewById(R.id.fab);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "fab", Toast.LENGTH_LONG).show();
                }
            });
        }

        recyclerView = rootView.findViewById(R.id.fragment_recycler);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mUserTasks = firebaseDatabase.child("users").child(user.getUid()).child("assignments");

        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        //TODO: fill in task array goes here


        Log.i("COUNT ARRAY LIST0", String.valueOf(tasks.size()));
        do{
            DatabaseReference mTempTask = mUserTasks.child(String.valueOf(i));
            Log.i("COUNT ARRAY LIST2", String.valueOf(tasks.size()));
            mTempTask.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String duedate = dataSnapshot.child("duedate").getValue(String.class);
                    String done = dataSnapshot.child("done").getValue(String.class);
                    String clas = dataSnapshot.child("class").getValue(String.class);
                    Tasks temp = new Tasks(name, duedate, done, clas);
                    Log.i("TASK ******::::::::::", name + " " + duedate + " " + done + " " + clas);
                    if(name != null)
                        tasks.add(temp);
                    Log.i("55COUNT ARRAY LIST1", String.valueOf(tasks.size()));
                    if(name == null ){
                        Log.i("COUNT ARRAY LIST", String.valueOf(tasks.size()));
                        AdapterTasks adapterProduct = new AdapterTasks(0, getActivity(), tasks);
                        recyclerView.setAdapter(adapterProduct);
                        numChild = tasks.size();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            i++;
            Log.i("COUNT ARRAY LIST1", String.valueOf(tasks.size()));
        }while (i < 15);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
