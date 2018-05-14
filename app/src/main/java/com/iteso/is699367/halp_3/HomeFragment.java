package com.iteso.is699367.halp_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.halp_3.beans.Classes;
import com.iteso.is699367.halp_3.beans.Tasks;
import com.iteso.is699367.halp_3.database.DataBaseHandler;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    DatabaseReference firebaseDatabase;
    DatabaseReference mUserTasks;
    DatabaseReference mUserClasses;
    FirebaseAuth mAuth;
    ArrayList<Tasks> tasks = new ArrayList<Tasks>();
    ArrayList<Classes> classes = new ArrayList<Classes>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_recycler);
        recyclerView1 = rootView.findViewById(R.id.fragment_recycler1);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mUserTasks = firebaseDatabase.child("users").child(user.getUid()).child("assignments");
        mUserClasses = firebaseDatabase.child("users").child(user.getUid()).child("classes");
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mUserTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //User value = dataSnapshot.getValue(User.class);
                //message.setText(value.toString());
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Tasks value = snapshot.getValue(Tasks.class);
                    Log.d("FIREBASE", "Key is: " + snapshot.getKey() + " Value is: " + snapshot.getValue());
                    tasks.add(value);
                }
                Log.i("COUNT ARRAY LIST", String.valueOf(tasks.size()));
                AdapterTasks adapterProduct = new AdapterTasks(0, getActivity(), tasks);
                recyclerView.setAdapter(adapterProduct);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        recyclerView1.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(mLayoutManager1);
        mUserClasses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //User value = dataSnapshot.getValue(User.class);
                //message.setText(value.toString());
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Classes value = snapshot.getValue(Classes.class);
                    Log.d("FIREBASE", "Key is: " + snapshot.getKey() + " Value is: " + snapshot.getValue());
                    classes.add(value);
                }
                Log.i("COUNT ARRAY LIST", String.valueOf(classes.size()));
                AdapterClasses adapterProduct1 = new AdapterClasses(0, getActivity(), classes);
                recyclerView1.setAdapter(adapterProduct1);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
